package com.truelayer.pokedex.domain.pokemon;

import java.util.Optional;

public interface PokeApiPort {
  Optional<Pokemon> getPokemon(String pokemonName);
}
