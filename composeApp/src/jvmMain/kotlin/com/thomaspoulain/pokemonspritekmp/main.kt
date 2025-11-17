package com.thomaspoulain.pokemonspritekmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.thomaspoulain.pokemonspritekmp.presentation.App
import org.jetbrains.compose.resources.stringResource
import pokemonspritekmp.composeapp.generated.resources.Res
import pokemonspritekmp.composeapp.generated.resources.app_title

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = stringResource(Res.string.app_title),
    ) {
        App()
    }
}