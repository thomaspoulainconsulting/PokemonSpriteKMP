package com.thomaspoulain.pokemonspritekmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.thomaspoulain.pokemonspritekmp.screen.PokemonScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        PokemonScreen()
    }
}