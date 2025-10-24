package com.thomaspoulain.pokemonspritekmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.thomaspoulain.pokemonspritekmp.di.sharedModule
import com.thomaspoulain.pokemonspritekmp.screen.PokemonScreen
import okio.FileSystem
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinMultiplatformApplication
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.KoinConfiguration

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App() {
    MaterialTheme {

        KoinMultiplatformApplication(
            config = KoinConfiguration {
                modules(sharedModule)
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