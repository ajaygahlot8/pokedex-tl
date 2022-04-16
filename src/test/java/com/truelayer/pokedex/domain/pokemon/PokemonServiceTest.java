package com.truelayer.pokedex.domain.pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PokemonServiceTest {

  PokemonService pokemonService;

  @Mock PokeApiPort pokeApiPort;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
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

    when(pokeApiPort.getPokemon(pokemonName)).thenReturn(Optional.of(pokemon));

    var result = pokemonService.getPokemon(pokemonName);

    verify(pokeApiPort, times(1)).getPokemon(pokemonName);
    assertEquals(result, pokemon);
  }

  @Test
  void testGetPokemonIfPokemonNotFound() {
    var pokemonName = "mewtwo";

    when(pokeApiPort.getPokemon(pokemonName)).thenReturn(Optional.empty());

    assertThrows(PokemonNotFoundException.class, () -> pokemonService.getPokemon(pokemonName));
  }
}
