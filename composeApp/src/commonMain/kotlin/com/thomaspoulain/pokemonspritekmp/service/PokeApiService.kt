package com.thomaspoulain.pokemonspritekmp.service

import com.thomaspoulain.pokemonspritekmp.dto.PokemonDto
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query

interface PokeApiService {

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: String): PokemonDto
}