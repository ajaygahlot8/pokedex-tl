package com.truelayer.pokedex.domain.pokemon;

import org.springframework.stereotype.Service;

@Service
public class PokemonService {

  private final PokeApiPort pokeApiPort;

  public PokemonService(PokeApiPort pokeApiPort) {
    this.pokeApiPort = pokeApiPort;
  }

  public Pokemon getPokemon(String pokemonName) {
    return pokeApiPort.getPokemon(pokemonName);
  }
}
