package com.thomaspoulain.pokemonspritekmp.data.di

import com.thomaspoulain.pokemonspritekmp.data.repository.PokeRepositoryImpl
import com.thomaspoulain.pokemonspritekmp.data.service.PokeApiService
import com.thomaspoulain.pokemonspritekmp.data.service.createPokeApiService
import com.thomaspoulain.pokemonspritekmp.domain.repository.PokeRepository
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val sharedModule = module {
    single<Ktorfit> {
        Ktorfit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .httpClient(
                HttpClient {
                    install(ContentNegotiation) {
                        json(
                            Json {
                                prettyPrint = true
                                ignoreUnknownKeys = true
                            }
                        )
                    }
                })
            .build()
    }

    single<PokeApiService> {
        get<Ktorfit>().createPokeApiService()
    }
    single<PokeRepository> {
        PokeRepositoryImpl(get())
    }
}