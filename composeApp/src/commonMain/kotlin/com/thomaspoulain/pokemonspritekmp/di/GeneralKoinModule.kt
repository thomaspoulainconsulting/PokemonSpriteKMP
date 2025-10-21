package com.thomaspoulain.pokemonspritekmp.di

import com.thomaspoulain.pokemonspritekmp.viewmodel.PokeListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sharedModule = module {
    viewModelOf(::PokeListViewModel)
}