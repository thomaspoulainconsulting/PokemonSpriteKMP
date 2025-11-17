package com.thomaspoulain.pokemonspritekmp.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import coil3.compose.AsyncImage
import com.thomaspoulain.pokemonspritekmp.data.dto.Generation
import com.thomaspoulain.pokemonspritekmp.data.dto.PokemonDto
import com.thomaspoulain.pokemonspritekmp.data.dto.SpriteObject
import com.thomaspoulain.pokemonspritekmp.data.dto.gamesOf
import com.thomaspoulain.pokemonspritekmp.data.dto.spriteOf

@Composable
fun PokemonDetails(
    details: PokemonDto,
) {
    val locale = Locale.current
    Column {
        Text(
            text = details.name.capitalize(locale),
            style = MaterialTheme.typography.headlineMedium
        )

        Generation.entries.forEach { generation ->
            details.sprites.gamesOf(generation).forEach {
                SpriteDetails(
                    name = it.first.capitalize(locale),
                    sprite = details.sprites.spriteOf(generation.key, it.first)
                )
            }
        }
    }
}

@Composable
fun SpriteDetails(
    name: String,
    sprite: SpriteObject?,
) {
    Text(
        text = name,
        style = MaterialTheme.typography.bodyLarge
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = sprite?.frontDefault,
            contentDescription = null,
        )
        sprite?.frontShiny?.let {
            AsyncImage(
                model = it,
                contentDescription = null,
            )
        }
        sprite?.backDefault?.let {
            AsyncImage(
                model = it,
                contentDescription = null,
            )
        }
        sprite?.backShiny?.let {
            AsyncImage(
                model = it,
                contentDescription = null,
            )
        }
    }
}