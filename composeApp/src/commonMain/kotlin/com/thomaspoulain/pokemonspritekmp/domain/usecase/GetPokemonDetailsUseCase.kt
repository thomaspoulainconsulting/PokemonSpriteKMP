package com.thomaspoulain.pokemonspritekmp.domain.usecase

import com.thomaspoulain.pokemonspritekmp.data.dto.PokemonDto
import com.thomaspoulain.pokemonspritekmp.domain.model.PokemonId
import com.thomaspoulain.pokemonspritekmp.domain.repository.PokeRepository

class GetPokemonDetailsUseCase(
    private val repository: PokeRepository,
) {
    suspend operator fun invoke(id: PokemonId): Result<PokemonDto> = repository.getPokemonDetails(id)
}
