package com.thomaspoulain.pokemonspritekmp.data.repository

import com.thomaspoulain.pokemonspritekmp.data.dto.PokemonDto
import com.thomaspoulain.pokemonspritekmp.data.service.PokeApiService
import com.thomaspoulain.pokemonspritekmp.domain.model.Generation
import com.thomaspoulain.pokemonspritekmp.domain.model.Pokemon
import com.thomaspoulain.pokemonspritekmp.domain.model.PokemonId
import com.thomaspoulain.pokemonspritekmp.domain.repository.PokeRepository

class PokeRepositoryImpl(
    private val service: PokeApiService,
) : PokeRepository {
    override suspend fun getPokemonDetails(id: PokemonId): PokemonDto = service.getPokemon(id)

    override suspend fun getPokemonFromGeneration(generation: Generation): List<Pokemon> =
        generation.range.map {
            Pokemon(
                id = it.toString(),
                image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$it.png",
                generation = generation,
            )
        }
}