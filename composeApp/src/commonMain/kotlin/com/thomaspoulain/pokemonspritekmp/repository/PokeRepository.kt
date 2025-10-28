package com.thomaspoulain.pokemonspritekmp.repository

import com.thomaspoulain.pokemonspritekmp.dto.PokemonDto
import com.thomaspoulain.pokemonspritekmp.service.PokeApiService

class PokeRepository(
    private val service: PokeApiService,
) {
    suspend fun getPokemon(id: String): PokemonDto = service.getPokemon(id)
}