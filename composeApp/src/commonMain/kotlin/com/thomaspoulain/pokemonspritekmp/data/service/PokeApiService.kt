package com.thomaspoulain.pokemonspritekmp.data.service

import com.thomaspoulain.pokemonspritekmp.data.dto.PokemonDto
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path

interface PokeApiService {

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: String): PokemonDto
}