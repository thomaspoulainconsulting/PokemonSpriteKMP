package com.thomaspoulain.pokemonspritekmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform