package com.thomaspoulain.pokemonspritekmp.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
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
import com.thomaspoulain.pokemonspritekmp.data.dto.PokemonDto
import com.thomaspoulain.pokemonspritekmp.domain.model.ErrorDetails
import com.thomaspoulain.pokemonspritekmp.domain.model.Generation
import com.thomaspoulain.pokemonspritekmp.domain.model.Pokemon
import com.thomaspoulain.pokemonspritekmp.domain.model.PokemonData
import com.thomaspoulain.pokemonspritekmp.presentation.composable.Filters
import com.thomaspoulain.pokemonspritekmp.presentation.composable.Item
import com.thomaspoulain.pokemonspritekmp.presentation.composable.ItemLoading
import com.thomaspoulain.pokemonspritekmp.presentation.composable.PokemonDetails
import com.thomaspoulain.pokemonspritekmp.presentation.state.PokeDetailsState
import com.thomaspoulain.pokemonspritekmp.presentation.state.PokeState
import com.thomaspoulain.pokemonspritekmp.presentation.viewmodel.PokeListViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import pokemonspritekmp.composeapp.generated.resources.Res
import pokemonspritekmp.composeapp.generated.resources.app_title

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonScreen() {
    val vm: PokeListViewModel = koinViewModel()
    val state by vm.state.collectAsStateWithLifecycle()
    val pokemonDetails by vm.pokemonDetails.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(Res.string.app_title))
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
                pokemonDetails = (data.pokemonDetails as? PokeDetailsState.Success)?.details,
                listState = listState,
                onPokemonClicked = onPokemonClicked
            )
        }

        is PokeState.Failure -> {
            ContentError(data.state.error)
        }
    }
}

@Composable
fun ContentError(errorDetails: ErrorDetails) {
    Text(
        text = errorDetails.message,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentSuccess(
    items: ImmutableList<Pokemon>,
    pokemonDetails: PokemonDto?,
    listState: LazyGridState,
    onPokemonClicked: (String) -> Unit
) {
    var displayBottomSheet by remember(pokemonDetails) { mutableStateOf(pokemonDetails != null) }

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

        if (displayBottomSheet && pokemonDetails != null) {
            ModalBottomSheet(
                onDismissRequest = {
                    displayBottomSheet = false
                }
            ) {
                PokemonDetails(pokemonDetails)
            }
        }
    }
}

