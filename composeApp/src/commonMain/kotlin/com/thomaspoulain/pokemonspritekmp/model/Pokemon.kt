package com.thomaspoulain.pokemonspritekmp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val id: Int,
    val sprites: Sprites ? = null,
    val name: String,
)

@Serializable
data class Sprites(
    val other: OtherSprite,
)

@Serializable
data class OtherSprite(
    @SerialName("dream_world") val dreamWorld: DreamWorld,
    @SerialName("official-artwork") val officialArtwork: OfficialArtwork,
)

@Serializable
data class DreamWorld(
    @SerialName("front_default") val front: String,
)

@Serializable
data class OfficialArtwork(
    @SerialName("front_default") val front: String,
    @SerialName("front_shiny") val shiny: String,
)