package com.thomaspoulain.pokemonspritekmp.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.thomaspoulain.pokemonspritekmp.model.Pokemon

@Composable
fun Item(data: Pokemon, onClick: (String) -> Unit) {
    AsyncImage(
        modifier = Modifier.defaultMinSize(80.dp).clickable(
            onClick = { onClick(data.id) }
        ),
        model = data.image,
        contentDescription = null,
    )
}