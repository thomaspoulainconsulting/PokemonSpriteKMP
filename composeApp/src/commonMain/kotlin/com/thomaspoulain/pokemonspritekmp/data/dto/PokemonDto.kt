package com.thomaspoulain.pokemonspritekmp.data.dto

import androidx.compose.runtime.Stable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Stable
data class PokemonDto(
    val id: Int,
    val name: String,
    val cries: Cries,
    val sprites: Sprites,
)

@Serializable
data class Cries(
    val latest: String,
    val legacy: String,
)

@Serializable
data class Sprites(
    val versions: Map<String, Map<String, SpriteObject>> = emptyMap()
)

@Serializable
data class SpriteObject(
    @SerialName("front_default") val frontDefault: String? = null,
    @SerialName("front_shiny") val frontShiny: String? = null,
    @SerialName("back_default") val backDefault: String? = null,
    @SerialName("back_shiny") val backShiny: String? = null,
)

fun Sprites.gamesOf(generationKey: Generation): List<Pair<String, SpriteObject>> =
    versions[generationKey.key]?.map { it.key to it.value }.orEmpty()

fun Sprites.spriteOf(generationKey: String, gameKey: String): SpriteObject? =
    versions[generationKey]?.get(gameKey)

/* ------------------------- (Optionnel) Clés connues ------------------------- */

enum class Generation(val key: String) {
    GEN1("generation-i"),
    GEN2("generation-ii"),
    GEN3("generation-iii"),
    GEN4("generation-iv"),
    GEN5("generation-v"),
    GEN6("generation-vi");
}