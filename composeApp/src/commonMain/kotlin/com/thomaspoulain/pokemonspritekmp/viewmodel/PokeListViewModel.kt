package com.thomaspoulain.pokemonspritekmp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thomaspoulain.pokemonspritekmp.model.Generation
import com.thomaspoulain.pokemonspritekmp.model.Pokemon
import com.thomaspoulain.pokemonspritekmp.state.PokeState
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokeListViewModel() : ViewModel() {
    private val _state = MutableStateFlow<PokeState>(PokeState.Loading)
    val state = _state.asStateFlow()

    init {
        loadPokedex(Generation.Gen1)
    }

    fun loadPokedex(generation: Generation) = viewModelScope.launch {
        runCatching {
            _state.value = PokeState.Loading
            generation.range.map {
                Pokemon(
                    id = it,
                    name = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$it.png",
                )
            }
        }.onSuccess {
            _state.value = PokeState.Success(it)
        }.onFailure {
            _state.value = PokeState.Error(it)
        }
    }
}