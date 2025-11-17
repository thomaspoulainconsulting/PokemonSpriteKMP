package com.thomaspoulain.pokemonspritekmp.domain.usecase

import com.thomaspoulain.pokemonspritekmp.domain.model.Generation
import com.thomaspoulain.pokemonspritekmp.domain.model.Pokemon
import com.thomaspoulain.pokemonspritekmp.domain.repository.PokeRepository

class GetPokemonFromGenerationUseCase(
    private val pokemonRepository: PokeRepository,
) {
    suspend fun getPokemon(generation: Generation): List<Pokemon> =
        pokemonRepository.getPokemonFromGeneration(generation)
}