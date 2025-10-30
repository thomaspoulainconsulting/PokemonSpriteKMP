package com.thomaspoulain.pokemonspritekmp.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
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

data class GameSprite(
    val generationKey: String,
    val gameKey: String,
    val sprite: SpriteObject
)

fun Sprites.gamesOf(generationKey: Generation): List<Pair<String, SpriteObject>> =
    versions[generationKey.key]?.map { it.key to it.value }.orEmpty()

fun Sprites.allGameSprites(): List<GameSprite> =
    versions.flatMap { (gen, games) ->
        games.map { (game, sprite) -> GameSprite(gen, game, sprite) }
    }

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

object GamesGen1 {
    const val RED_BLUE = "red-blue"
    const val YELLOW = "yellow"
}

object GamesGen2 {
    const val CRYSTAL = "crystal"
    const val GOLD = "gold"
    const val SILVER = "silver"
}

object GamesGen3 {
    const val FIRE_RED_LEAF_GREEN = "firered-leafgreen"
    const val RUBY_SAPPHIRE = "ruby-sapphire"
    const val EMERALD = "emerald"
}

object GamesGen4 {
    const val DIAMOND_PEARL = "diamond-pearl"
    const val HEARTGOLD_SOULSILVER = "heartgold-soulsilver"
    const val PLATINUM = "platinum"
}