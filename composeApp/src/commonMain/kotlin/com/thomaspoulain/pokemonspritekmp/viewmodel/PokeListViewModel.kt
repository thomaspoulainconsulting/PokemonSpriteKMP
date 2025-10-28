package com.thomaspoulain.pokemonspritekmp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thomaspoulain.pokemonspritekmp.model.Generation
import com.thomaspoulain.pokemonspritekmp.model.Pokemon
import com.thomaspoulain.pokemonspritekmp.repository.PokeRepository
import com.thomaspoulain.pokemonspritekmp.state.PokeDetailsState
import com.thomaspoulain.pokemonspritekmp.state.PokeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokeListViewModel(
    private val repository: PokeRepository
) : ViewModel() {
    private val _state = MutableStateFlow<PokeState>(PokeState.Loading)
    val state = _state.asStateFlow()

    private val _pokemonDetails = MutableStateFlow<PokeDetailsState>(PokeDetailsState.Idle)
    val pokemonDetails = _pokemonDetails.asStateFlow()


    init {
        loadPokedex(Generation.Gen1)
    }

    fun loadPokedex(generation: Generation) = viewModelScope.launch {
        runCatching {
            _state.value = PokeState.Loading
            generation.range.map {
                Pokemon(
                    id = it.toString(),
                    image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$it.png",
                    generation = generation,
                )
            }
        }.onSuccess {
            _state.value = PokeState.Success(items = it)

        }.onFailure {
            _state.value = PokeState.Error(it)
        }
    }

    fun getInformation(id: String) = viewModelScope.launch {
        runCatching {
            _pokemonDetails.value = PokeDetailsState.Loading
            repository.getPokemon(id)
        }.onSuccess {
            _pokemonDetails.value = PokeDetailsState.Success(it)
        }.onFailure {
            _pokemonDetails.value = PokeDetailsState.Error(it)
        }
    }
}