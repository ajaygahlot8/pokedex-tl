package com.truelayer.pokedex.domain.pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class PokemonServiceTest {

  PokemonService pokemonService;

  @Mock PokeApiPort pokeApiPort;

  @BeforeEach
  void setup() {
    pokemonService = new PokemonService(pokeApiPort);
  }

  @Test
  void testGetPokemon() {
    var pokemonName = "mewtwo";
    var description =
        "It was created by a scientist after years of horrific\fgene splicing and DNA engineering experiments.";
    var pokemon =
        Pokemon.builder()
            .name(pokemonName)
            .description(description)
            .habitat("rare")
            .isLegendary(true)
            .build();

    when(pokeApiPort.getPokemon(pokemonName)).thenReturn(pokemon);

    var result = pokemonService.getPokemon(pokemonName);

    verify(pokeApiPort, times(1)).getPokemon(pokemonName);
    assertEquals(result, pokemon);
  }
}
