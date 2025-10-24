package com.thomaspoulain.pokemonspritekmp.composable

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.thomaspoulain.pokemonspritekmp.model.Pokemon

@Composable
fun Item(data: Pokemon) {
    AsyncImage(
        modifier = Modifier.defaultMinSize(80.dp),
        model = data.image,
        contentDescription = null,
    )
}