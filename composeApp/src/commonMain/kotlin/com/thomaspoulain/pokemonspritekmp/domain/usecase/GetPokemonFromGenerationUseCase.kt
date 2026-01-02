package com.thomaspoulain.pokemonspritekmp.domain.usecase

import com.thomaspoulain.pokemonspritekmp.domain.model.Generation
import com.thomaspoulain.pokemonspritekmp.domain.model.Pokemon
import com.thomaspoulain.pokemonspritekmp.domain.repository.PokeRepository

class GetPokemonFromGenerationUseCase(
    private val repository: PokeRepository,
) {
    suspend operator fun invoke(generation: Generation): Result<List<Pokemon>> =
        repository.getPokemonFromGeneration(generation)
}
