package com.thomaspoulain.pokemonspritekmp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.thomaspoulain.pokemonspritekmp.model.Generation
import com.thomaspoulain.pokemonspritekmp.model.Pokemon
import com.thomaspoulain.pokemonspritekmp.state.PokeState
import com.thomaspoulain.pokemonspritekmp.viewmodel.PokeListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App(vm: PokeListViewModel = koinViewModel()) {
    MaterialTheme {
        Column(
            modifier = Modifier
                .background(Color.White)
                .safeContentPadding()
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
private fun MainContent(
    state: PokeState,
    onFilterChange: (Generation) -> Unit
) {
    var filter: Generation by remember { mutableStateOf(Generation.Gen1) }
    Filters(
        filter = filter,
        onFilterChange = { filter = it }
    )

    when (state) {
        is PokeState.Loading -> {
            //ContentLoading()
        }

        is PokeState.Success -> {
            ContentSuccess(state.items, filter)
        }

        is PokeState.Error -> {
            // todo
        }
    }
}

@Composable
private fun Filters(
    filter: Generation,
    onFilterChange: (Generation) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Generation.entries.forEach {
            FilterChip(
                selected = filter == it,
                label = { Text(it.name) },
                onClick = { onFilterChange(it) },
            )
        }
    }
}

@Composable
private fun ContentSuccess(items: List<Pokemon>, filter: Generation) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            items(items) {
                Item(
                    modifier = Modifier.animateItem(),
                    data = it,
                )
            }
        })
}

@Composable
private fun Item(
    modifier: Modifier = Modifier, data: Pokemon
) {
    AsyncImage(
        modifier = modifier,
        model = data.name,
        contentDescription = null,
    )
}