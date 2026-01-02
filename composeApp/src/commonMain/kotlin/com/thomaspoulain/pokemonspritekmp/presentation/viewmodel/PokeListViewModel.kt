package com.thomaspoulain.pokemonspritekmp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thomaspoulain.pokemonspritekmp.domain.model.ErrorDetails
import com.thomaspoulain.pokemonspritekmp.domain.model.Generation
import com.thomaspoulain.pokemonspritekmp.domain.usecase.GetPokemonDetailsUseCase
import com.thomaspoulain.pokemonspritekmp.domain.usecase.GetPokemonFromGenerationUseCase
import com.thomaspoulain.pokemonspritekmp.presentation.state.PokeDetailsState
import com.thomaspoulain.pokemonspritekmp.presentation.state.PokeState
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokeListViewModel(
    private val getPokemonFromGenerationUseCase: GetPokemonFromGenerationUseCase,
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase,
) : ViewModel() {
    val state: StateFlow<PokeState>
        field = MutableStateFlow<PokeState>(PokeState.Loading)

    val pokemonDetails: StateFlow<PokeDetailsState>
        field = MutableStateFlow<PokeDetailsState>(PokeDetailsState.Idle)

    init {
        loadPokedex(Generation.Gen1)
    }

    fun loadPokedex(generation: Generation) = viewModelScope.launch {
        state.value = PokeState.Loading
        state.value = getPokemonFromGenerationUseCase(generation)
            .fold(
                onSuccess = { PokeState.Success(items = it.toImmutableList()) },
                onFailure = { PokeState.Failure(ErrorDetails(it.message.orEmpty())) }
            )
    }

    fun getInformation(id: String) = viewModelScope.launch {
        pokemonDetails.value = PokeDetailsState.Loading
        pokemonDetails.value = getPokemonDetailsUseCase(id).fold(
            onSuccess = { PokeDetailsState.Success(it) },
            onFailure = { PokeDetailsState.Error(it) }
        )
    }
}