package com.thomaspoulain.pokemonspritekmp.domain.di

import com.thomaspoulain.pokemonspritekmp.domain.usecase.GetPokemonDetailsUseCase
import com.thomaspoulain.pokemonspritekmp.domain.usecase.GetPokemonFromGenerationUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainKoinModule = module {
    singleOf(::GetPokemonDetailsUseCase)
    singleOf(::GetPokemonFromGenerationUseCase)
}