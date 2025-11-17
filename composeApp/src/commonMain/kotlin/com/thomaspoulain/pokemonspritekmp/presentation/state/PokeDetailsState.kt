package com.thomaspoulain.pokemonspritekmp.presentation.state

import com.thomaspoulain.pokemonspritekmp.data.dto.PokemonDto

sealed interface PokeDetailsState {
    data object Idle : PokeDetailsState
    data object Loading : PokeDetailsState
    data class Success(val details: PokemonDto) : PokeDetailsState
    data class Error(val throwable: Throwable) : PokeDetailsState
}