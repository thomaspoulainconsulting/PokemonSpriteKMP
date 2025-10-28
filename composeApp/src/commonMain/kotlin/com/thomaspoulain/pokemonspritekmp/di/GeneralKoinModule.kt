package com.thomaspoulain.pokemonspritekmp.di

import com.thomaspoulain.pokemonspritekmp.repository.PokeRepository
import com.thomaspoulain.pokemonspritekmp.service.PokeApiService
import com.thomaspoulain.pokemonspritekmp.service.createPokeApiService
import com.thomaspoulain.pokemonspritekmp.viewmodel.PokeListViewModel
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
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
    singleOf(::PokeRepository)


    viewModelOf(::PokeListViewModel)
}