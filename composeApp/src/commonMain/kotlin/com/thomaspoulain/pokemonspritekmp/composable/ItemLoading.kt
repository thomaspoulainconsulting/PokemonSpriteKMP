package com.thomaspoulain.pokemonspritekmp.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ItemLoading() {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .background(color = Color.Gray)
            .size(48.dp),
        content = {},
    )
}

@Composable
@Preview
private fun Preview() {
    MaterialTheme {
        ItemLoading()
    }
}