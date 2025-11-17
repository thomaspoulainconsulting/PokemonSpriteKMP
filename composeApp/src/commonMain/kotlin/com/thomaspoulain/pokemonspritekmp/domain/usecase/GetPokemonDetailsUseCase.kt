package com.thomaspoulain.pokemonspritekmp.domain.usecase

import com.thomaspoulain.pokemonspritekmp.domain.model.PokemonId
import com.thomaspoulain.pokemonspritekmp.domain.repository.PokeRepository

class GetPokemonDetailsUseCase(
    private val repository: PokeRepository,
) {
    suspend fun getPokemonDetails(id: PokemonId) = repository.getPokemonDetails(id)
}