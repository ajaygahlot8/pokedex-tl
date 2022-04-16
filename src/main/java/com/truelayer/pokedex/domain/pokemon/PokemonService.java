package com.truelayer.pokedex.domain.pokemon;

import com.truelayer.pokedex.exception.ErrorCode;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {

  private final PokeApiPort pokeApiPort;

  public PokemonService(PokeApiPort pokeApiPort) {
    this.pokeApiPort = pokeApiPort;
  }

  public Pokemon getPokemon(String pokemonName) {
    return pokeApiPort
        .getPokemon(pokemonName)
        .orElseThrow(() -> new PokemonNotFoundException(ErrorCode.P1));
  }
}
