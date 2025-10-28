package com.thomaspoulain.pokemonspritekmp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.thomaspoulain.pokemonspritekmp.composable.Filters
import com.thomaspoulain.pokemonspritekmp.composable.Item
import com.thomaspoulain.pokemonspritekmp.composable.ItemLoading
import com.thomaspoulain.pokemonspritekmp.model.Generation
import com.thomaspoulain.pokemonspritekmp.model.Pokemon
import com.thomaspoulain.pokemonspritekmp.model.PokemonData
import com.thomaspoulain.pokemonspritekmp.state.PokeDetailsState
import com.thomaspoulain.pokemonspritekmp.state.PokeState
import com.thomaspoulain.pokemonspritekmp.viewmodel.PokeListViewModel
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonScreen(
    vm: PokeListViewModel = koinViewModel()
) {
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
            val pokemonDetails by vm.pokemonDetails.collectAsStateWithLifecycle()

            MainContent(
                data = PokemonData(
                    state = state,
                    pokemonDetails = pokemonDetails,
                ),
                onFilterChange = {
                    vm.loadPokedex(it)
                },
                onPokemonClicked = vm::getInformation,
            )
        }
    }
}


@Composable
fun MainContent(
    data: PokemonData,
    onFilterChange: (generation: Generation) -> Unit,
    onPokemonClicked: (id: String) -> Unit,
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

    when (data.state) {
        is PokeState.Loading -> {
            ContentLoading()
        }

        is PokeState.Success -> {
            ContentSuccess(
                items = data.state.items,
                detailsState = data.pokemonDetails,
                listState, onPokemonClicked
            )
        }

        is PokeState.Error -> {
            ContentError(data.state.throwable)
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
fun ContentSuccess(
    items: List<Pokemon>,
    detailsState: PokeDetailsState,
    listState: LazyGridState,
    onPokemonClicked: (String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        LazyVerticalGrid(
            modifier = Modifier.weight(1f),
            state = listState,
            columns = GridCells.Adaptive(minSize = 120.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            content = {
                items(items) { data ->
                    Item(data, onPokemonClicked)
                }
            }
        )


        when (detailsState) {
            is PokeDetailsState.Loading -> {
                Text("loading")
            }

            is PokeDetailsState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(150.dp)
                ) {
                    Text(
                        text = detailsState.details.name.capitalize(),
                        modifier = Modifier.padding(8.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        AsyncImage(
                            model = detailsState.details.sprites.versions.generation3.fireredLeafgreen.frontDefault,
                            contentDescription = null,
                        )
                        detailsState.details.sprites.versions.generation3.fireredLeafgreen.frontShiny?.let {
                            AsyncImage(
                                model = it,
                                contentDescription = null,
                            )
                        }
                    }
                }
            }

            else -> {
                // nothing
            }
        }
    }
}

