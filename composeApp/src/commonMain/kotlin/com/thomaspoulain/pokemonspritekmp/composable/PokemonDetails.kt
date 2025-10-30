package com.thomaspoulain.pokemonspritekmp.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.thomaspoulain.pokemonspritekmp.dto.Generation
import com.thomaspoulain.pokemonspritekmp.dto.PokemonDto
import com.thomaspoulain.pokemonspritekmp.dto.SpriteObject
import com.thomaspoulain.pokemonspritekmp.dto.gamesOf
import com.thomaspoulain.pokemonspritekmp.dto.spriteOf

@Composable
fun PokemonDetails(
    details: PokemonDto,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .width(300.dp)
    ) {
        Text(
            text = details.name.capitalize(),
            style = MaterialTheme.typography.headlineMedium
        )

        HorizontalDivider(modifier = Modifier.padding(bottom = 16.dp))

        Generation.entries.forEach { generation ->
            details.sprites.gamesOf(generation).forEach {
                SpriteDetails(
                    name = it.first.capitalize(),
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