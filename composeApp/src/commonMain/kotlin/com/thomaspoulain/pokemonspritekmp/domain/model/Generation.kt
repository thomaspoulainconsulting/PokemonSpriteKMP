package com.thomaspoulain.pokemonspritekmp.domain.model

enum class Generation(val range: IntRange) {
    Gen1(1..151),
    Gen2(152..251),
    Gen3(252..386),
    Gen4(387..493),
    Gen5(494..649),
}