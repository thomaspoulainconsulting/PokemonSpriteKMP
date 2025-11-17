package com.thomaspoulain.pokemonspritekmp.presentation

import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialExpressiveTheme
import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.thomaspoulain.pokemonspritekmp.data.di.sharedModule
import com.thomaspoulain.pokemonspritekmp.domain.di.domainKoinModule
import com.thomaspoulain.pokemonspritekmp.presentation.di.presentationKoinModule
import com.thomaspoulain.pokemonspritekmp.presentation.screen.PokemonScreen
import okio.FileSystem
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinMultiplatformApplication
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.KoinConfiguration

@OptIn(KoinExperimentalAPI::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
@Preview
fun App() {
    MaterialExpressiveTheme {
        KoinMultiplatformApplication(
            config = KoinConfiguration {
                modules(sharedModule + domainKoinModule + presentationKoinModule)
            }
        ) {
            setSingletonImageLoaderFactory { context ->
                ImageLoader.Builder(context)
                    .crossfade(true)
                    .logger(DebugLogger())
                    .memoryCache {
                        MemoryCache.Builder()
                            .maxSizePercent(context, 0.3)
                            .strongReferencesEnabled(true)
                            .build()
                    }
                    .diskCache {
                        DiskCache.Builder()
                            .directory(FileSystem.SYSTEM_TEMPORARY_DIRECTORY / "image_cache")
                            .maxSizeBytes(512L * 1024 * 1024)
                            .build()
                    }
                    .build()
            }

            PokemonScreen()
        }
    }
}