package com.thomaspoulain.pokemonspritekmp.model

import com.thomaspoulain.pokemonspritekmp.state.PokeDetailsState
import com.thomaspoulain.pokemonspritekmp.state.PokeState

class PokemonData(
    val state: PokeState = PokeState.Loading,
    val pokemonDetails: PokeDetailsState = PokeDetailsState.Idle,
)