package com.truelayer.pokedex.domain.translate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.truelayer.pokedex.domain.pokemon.Pokemon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class TranslationServiceTest {

  private TranslationService translationService;

  @Mock private FunTranslationPort funTranslationPort;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
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

    when(funTranslationPort.translate(pokemon.getDescription(), TranslationType.YODA))
        .thenReturn(expectedDescription);

    var result = translationService.translateDescription(pokemon);

    verify(funTranslationPort, times(1)).translate(pokemon.getDescription(), TranslationType.YODA);
    assertEquals(expectedDescription, result);
  }

  @Test
  void testTranslateDescriptionWithCaveHabitat() {
    var pokemonName = "mewtwo";
    var description =
        "It was created by a scientist after years of horrific gene splicing and DNA engineering experiments.";
    var pokemon =
        Pokemon.builder()
            .name(pokemonName)
            .description(description)
            .habitat("cave")
            .isLegendary(false)
            .build();

    var expectedDescription =
        "created by a scientist after years of horrific gene splicing and DNA engineering experiments, It was";

    when(funTranslationPort.translate(pokemon.getDescription(), TranslationType.YODA))
        .thenReturn(expectedDescription);

    var result = translationService.translateDescription(pokemon);

    verify(funTranslationPort, times(1)).translate(pokemon.getDescription(), TranslationType.YODA);
    assertEquals(expectedDescription, result);
  }

  @Test
  void testTranslateDescriptionWithShakespeareTranslation() {
    var pokemonName = "mewtwo";
    var description =
        "It was created by a scientist after years of horrific gene splicing and DNA engineering experiments.";
    var pokemon =
        Pokemon.builder()
            .name(pokemonName)
            .description(description)
            .habitat("rare")
            .isLegendary(false)
            .build();

    var expectedDescription =
        "created by a scientist after years of horrific gene splicing and DNA engineering experiments, It was";

    when(funTranslationPort.translate(pokemon.getDescription(), TranslationType.SHAKESPEARE))
        .thenReturn(expectedDescription);

    var result = translationService.translateDescription(pokemon);

    verify(funTranslationPort, times(1))
        .translate(pokemon.getDescription(), TranslationType.SHAKESPEARE);
    assertEquals(expectedDescription, result);
  }
}
