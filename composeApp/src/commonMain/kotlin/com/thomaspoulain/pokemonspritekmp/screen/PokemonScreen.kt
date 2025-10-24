package com.thomaspoulain.pokemonspritekmp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.thomaspoulain.pokemonspritekmp.composable.Filters
import com.thomaspoulain.pokemonspritekmp.composable.Item
import com.thomaspoulain.pokemonspritekmp.composable.ItemLoading
import com.thomaspoulain.pokemonspritekmp.model.Generation
import com.thomaspoulain.pokemonspritekmp.model.Pokemon
import com.thomaspoulain.pokemonspritekmp.state.PokeState
import com.thomaspoulain.pokemonspritekmp.viewmodel.PokeListViewModel
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonScreen(vm: PokeListViewModel = koinViewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Pokémon Artwork")
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            val state by vm.state.collectAsStateWithLifecycle()

            MainContent(
                state = state,
                onFilterChange = {
                    vm.loadPokedex(it)
                }
            )
        }
    }
}


@Composable
fun MainContent(
    state: PokeState,
    onFilterChange: (Generation) -> Unit
) {
    var filter by remember { mutableStateOf(Generation.Gen1) }
    val listState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()

    Filters(
        filter = filter,
        onFilterChange = {
            filter = it
            onFilterChange(it)
            coroutineScope.launch {
                listState.scrollToItem(0)
            }
        }
    )

    when (state) {
        is PokeState.Loading -> {
            ContentLoading()
        }

        is PokeState.Success -> {
            ContentSuccess(state.items, listState)
        }

        is PokeState.Error -> {
            ContentError(state.throwable)
        }
    }
}

@Composable
fun ContentError(throwable: Throwable) {
    Text(
        text = throwable.toString()
    )
}

@Composable
fun ContentLoading() {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            items(count = 20) {
                ItemLoading()
            }
        })
}

@Composable
fun ContentSuccess(items: List<Pokemon>, listState: LazyGridState) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        state = listState,
        columns = GridCells.Adaptive(minSize = 120.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            items(items) {
                Item(it)
            }
        }
    )
}

