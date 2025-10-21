package com.thomaspoulain.pokemonspritekmp.service

import com.thomaspoulain.pokemonspritekmp.model.Pokemon
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path

interface PokeApi {

    @GET("pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") id: Int
    ): Pokemon

}