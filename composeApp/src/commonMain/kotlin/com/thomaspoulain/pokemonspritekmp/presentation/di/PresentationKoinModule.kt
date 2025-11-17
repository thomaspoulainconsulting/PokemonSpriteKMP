package com.thomaspoulain.pokemonspritekmp.presentation.di

import com.thomaspoulain.pokemonspritekmp.presentation.viewmodel.PokeListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationKoinModule = module {
    viewModelOf(::PokeListViewModel)
}