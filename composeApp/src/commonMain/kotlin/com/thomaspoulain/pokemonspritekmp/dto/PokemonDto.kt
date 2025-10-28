package com.thomaspoulain.pokemonspritekmp.dto

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

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

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class Sprites(
    @JsonNames("front_default") val frontDefault: String,
    @JsonNames("front_shiny") val frontShiny: String,
    val versions: Versions,
)

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class Versions(
    @JsonNames("generation-i") val generation1: Generation1,
    @JsonNames("generation-ii") val generation2: Generation2,
    @JsonNames("generation-iii") val generation3: Generation3,
)

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class Generation1(
    @JsonNames("red-blue") val redBlue: SpriteObject,
    @JsonNames("yellow") val yellow: SpriteObject,
)

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class Generation2(
    @JsonNames("crystal") val crystal: SpriteObject,
    @JsonNames("gold") val gold: SpriteObject,
    @JsonNames("silver") val silver: SpriteObject,
)

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class Generation3(
    @JsonNames("emerald") val emerald: SpriteObject,
    @JsonNames("ruby-sapphire") val rubySapphire: SpriteObject,
    @JsonNames("firered-leafgreen") val fireredLeafgreen: SpriteObject,
)

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class SpriteObject(
    @JsonNames("front_default") val frontDefault: String,
    @JsonNames("front_shiny") val frontShiny: String? = null,
    @JsonNames("back_default") val backDefault: String? = null,
    @JsonNames("back_shiny") val backShiny: String? = null,
)