package com.truelayer.pokedex.domain.pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.truelayer.pokedex.domain.translate.TranslationService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PokemonServiceTest {

  private PokemonService pokemonService;

  @Mock private PokeApiPort pokeApiPort;

  @Mock private TranslationService translationService;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    pokemonService = new PokemonService(pokeApiPort, translationService);
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
    assertEquals(pokemon, result);
  }

  @Test
  void testGetPokemonIfPokemonNotFound() {
    var pokemonName = "mewtwo";

    when(pokeApiPort.getPokemon(pokemonName)).thenReturn(Optional.empty());

    assertThrows(PokemonNotFoundException.class, () -> pokemonService.getPokemon(pokemonName));
  }

  @Test
  void testGetTranslatedPokemon() {
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

    var translatedDescription =
        "created by a scientist after years of horrific gene splicing and DNA engineering experiments, It was";

    var expected =
        Pokemon.builder()
            .name(pokemonName)
            .description(translatedDescription)
            .habitat("rare")
            .isLegendary(true)
            .build();

    when(pokeApiPort.getPokemon(pokemonName)).thenReturn(Optional.of(pokemon));
    when(translationService.translateDescription(pokemon)).thenReturn(translatedDescription);

    var result = pokemonService.getTranslatedPokemon(pokemonName);

    verify(pokeApiPort, times(1)).getPokemon(pokemonName);
    verify(translationService, times(1)).translateDescription(pokemon);

    assertEquals(expected, result);
  }
}
