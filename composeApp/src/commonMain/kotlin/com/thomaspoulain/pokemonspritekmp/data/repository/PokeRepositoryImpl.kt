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
    override suspend fun getPokemonDetails(id: PokemonId): Result<PokemonDto> = runCatching {
        service.getPokemon(id)
    }

    override suspend fun getPokemonFromGeneration(generation: Generation): Result<List<Pokemon>> = runCatching {
        generation.range.map {
            Pokemon(
                id = it.toString(),
                image = "https://raw.githubusercontent.com/fanzeyi/pokemon.json/master/images/${it.toString().padStart(3, '0')}.png",
                generation = generation,
            )
        }
    }
}
