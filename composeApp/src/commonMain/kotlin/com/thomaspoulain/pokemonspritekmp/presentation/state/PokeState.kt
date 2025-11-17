package com.thomaspoulain.pokemonspritekmp.presentation.state

import com.thomaspoulain.pokemonspritekmp.domain.model.ErrorDetails
import com.thomaspoulain.pokemonspritekmp.domain.model.Pokemon
import kotlinx.collections.immutable.ImmutableList

sealed interface PokeState {
    data object Loading : PokeState
    data class Success(val items: ImmutableList<Pokemon>) : PokeState
    data class Failure(val error: ErrorDetails) : PokeState
}