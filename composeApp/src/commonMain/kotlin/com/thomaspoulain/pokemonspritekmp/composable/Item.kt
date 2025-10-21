package com.thomaspoulain.pokemonspritekmp.composable

import androidx.compose.runtime.Composable
import coil3.compose.AsyncImage
import com.thomaspoulain.pokemonspritekmp.model.Pokemon

@Composable
fun Item(data: Pokemon) {
    AsyncImage(
        model = data.image,
        contentDescription = null,
    )
}