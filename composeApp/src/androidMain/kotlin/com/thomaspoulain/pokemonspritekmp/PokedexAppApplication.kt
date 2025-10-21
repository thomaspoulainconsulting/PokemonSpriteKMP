package com.thomaspoulain.pokemonspritekmp

import android.app.Application
import com.thomaspoulain.pokemonspritekmp.di.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokedexAppApplication  : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PokedexAppApplication)
            modules(
                sharedModule,
            )
        }
    }
}