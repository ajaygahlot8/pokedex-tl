package com.truelayer.pokedex.adapter;

import com.truelayer.pokedex.domain.pokemon.PokeApiPort;
import com.truelayer.pokedex.domain.pokemon.Pokemon;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class PokeApiAdapter implements PokeApiPort {
  @Override
  public Optional<Pokemon> getPokemon(String pokemonName) {
    return Optional.empty();
  }
}
