package com.truelayer.pokedex.domain.translate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.truelayer.pokedex.domain.pokemon.Pokemon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TranslationServiceTest {

  TranslationService translationService;

  FunTranslationPort funTranslationPort;

  @BeforeEach
  void setup() {
    translationService = new TranslationService(funTranslationPort);
  }

  @Test
  void testTranslateDescription() {
    var pokemonName = "mewtwo";
    var description =
        "It was created by a scientist after years of horrific gene splicing and DNA engineering experiments.";
    var pokemon =
        Pokemon.builder()
            .name(pokemonName)
            .description(description)
            .habitat("rare")
            .isLegendary(true)
            .build();

    var expectedDescription =
        "created by a scientist after years of horrific gene splicing and DNA engineering experiments, It was";

    var result = translationService.translateDescription(pokemon);
    assertEquals(expectedDescription, result);
  }
}
