package com.thomaspoulain.pokemonspritekmp.domain.model

import androidx.compose.runtime.Stable
import com.thomaspoulain.pokemonspritekmp.presentation.state.PokeDetailsState
import com.thomaspoulain.pokemonspritekmp.presentation.state.PokeState

@Stable
data class PokemonData(
    val state: PokeState = PokeState.Loading,
    val pokemonDetails: PokeDetailsState = PokeDetailsState.Idle,
)