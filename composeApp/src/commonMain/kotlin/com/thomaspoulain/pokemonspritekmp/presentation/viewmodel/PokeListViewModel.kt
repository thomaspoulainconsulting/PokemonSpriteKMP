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
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokeListViewModel(
    private val getPokemonFromGenerationUseCase: GetPokemonFromGenerationUseCase,
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<PokeState>(PokeState.Loading)
    val state = _state.asStateFlow()

    private val _pokemonDetails = MutableStateFlow<PokeDetailsState>(PokeDetailsState.Idle)
    val pokemonDetails = _pokemonDetails.asStateFlow()


    init {
        loadPokedex(Generation.Gen1)
    }

    fun loadPokedex(generation: Generation) = viewModelScope.launch {
        _state.value = PokeState.Loading

        runCatching {
            getPokemonFromGenerationUseCase.getPokemon(generation)
        }.onSuccess {
            _state.value = PokeState.Success(items = it.toImmutableList())
        }.onFailure {
            _state.value = PokeState.Failure(ErrorDetails(it.message.orEmpty()))
        }
    }

    fun getInformation(id: String) = viewModelScope.launch {
        _pokemonDetails.value = PokeDetailsState.Loading

        runCatching {
            getPokemonDetailsUseCase.getPokemonDetails(id)
        }.onSuccess {
            _pokemonDetails.value = PokeDetailsState.Success(it)
        }.onFailure {
            _pokemonDetails.value = PokeDetailsState.Error(it)
        }
    }
}