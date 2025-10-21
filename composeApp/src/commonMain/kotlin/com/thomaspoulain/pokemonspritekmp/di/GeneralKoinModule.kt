package com.thomaspoulain.pokemonspritekmp.di

import com.thomaspoulain.pokemonspritekmp.viewmodel.PokeListViewModel
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sharedModule = module {
    single<Ktorfit> {
        val httpClient = HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }

        Ktorfit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .httpClient(httpClient)
            .build()
    }

//    single<PokeApi> {
//        get<Ktorfit>().create()
//    }

    viewModelOf(::PokeListViewModel)
}