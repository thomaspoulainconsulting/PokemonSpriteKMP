package com.thomaspoulain.pokemonspritekmp.state

import com.thomaspoulain.pokemonspritekmp.model.Pokemon

sealed interface PokeState {
    data object Loading : PokeState
    data class Success(val items: List<Pokemon>) : PokeState
    data class Error(val throwable: Throwable) : PokeState
}