package com.thomaspoulain.pokemonspritekmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.thomaspoulain.pokemonspritekmp.di.sharedModule
import org.koin.core.context.startKoin

fun main() = application {
    startKoin {
        modules(sharedModule)
    }
    Window(
        onCloseRequest = ::exitApplication,
        title = "PokemonSpriteKMP",
    ) {
        App()
    }
}