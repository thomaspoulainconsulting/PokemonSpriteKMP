package com.thomaspoulain.pokemonspritekmp.domain.repository

import com.thomaspoulain.pokemonspritekmp.data.dto.PokemonDto
import com.thomaspoulain.pokemonspritekmp.domain.model.Generation
import com.thomaspoulain.pokemonspritekmp.domain.model.Pokemon
import com.thomaspoulain.pokemonspritekmp.domain.model.PokemonId

interface PokeRepository {
    suspend fun getPokemonDetails(id: PokemonId): Result<PokemonDto>
    suspend fun getPokemonFromGeneration(generation: Generation): Result<List<Pokemon>>
}
