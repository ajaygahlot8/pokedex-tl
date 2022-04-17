package com.truelayer.pokedex.rest.controller;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.truelayer.pokedex.domain.pokemon.Pokemon;
import com.truelayer.pokedex.domain.pokemon.PokemonNotFoundException;
import com.truelayer.pokedex.domain.pokemon.PokemonService;
import com.truelayer.pokedex.exception.ErrorCode;
import com.truelayer.pokedex.rest.ApiError;
import com.truelayer.pokedex.rest.ApiResponse;
import com.truelayer.pokedex.rest.model.GetPokemonResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PokemonControllerTest {

  @Autowired MockMvc mockMvc;

  @MockBean PokemonService pokemonService;

  @Test
  @DisplayName("Should be able to get pokemon")
  void testGetPokemonWithSuccess() throws Exception {
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
    var expectedResponse =
        ApiResponse.createSuccessResponse(
            GetPokemonResponse.builder()
                .name(pokemonName)
                .description(description)
                .habitat("rare")
                .isLegendary(true)
                .build());

    when(pokemonService.getPokemon(pokemonName)).thenReturn(pokemon);

    mockMvc
        .perform(get("/pokemon/" + pokemonName))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(toJsonString(expectedResponse)));

    verify(pokemonService, times(1)).getPokemon(pokemonName);
  }

  @Test
  @DisplayName("Should be able to get translated pokemon")
  void testGetTranslatedPokemonWithSuccess() throws Exception {
    var pokemonName = "mewtwo";
    var updatedDescription =
        "It was created by a scientist after years of horrific\fgene splicing and DNA engineering experiments.";

    var pokemon =
        Pokemon.builder()
            .name(pokemonName)
            .description(updatedDescription)
            .habitat("rare")
            .isLegendary(true)
            .build();
    var expectedResponse =
        ApiResponse.createSuccessResponse(
            GetPokemonResponse.builder()
                .name(pokemonName)
                .description(updatedDescription)
                .habitat("rare")
                .isLegendary(true)
                .build());

    when(pokemonService.getTranslatedPokemon(pokemonName)).thenReturn(pokemon);

    mockMvc
        .perform(get("/pokemon/translated/" + pokemonName))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(toJsonString(expectedResponse)));

    verify(pokemonService, times(1)).getTranslatedPokemon(pokemonName);
  }

  @Test
  @DisplayName("Should not be able to get translated pokemon if pokemon not found")
  void testGetTranslatedPokemonWithFailure() throws Exception {
    var pokemonName = "mewtwo";

    var expectedResponse = ApiResponse.createErrorResponse(ApiError.fromErrorCode(ErrorCode.P1));

    doThrow(new PokemonNotFoundException(ErrorCode.P1))
        .when(pokemonService)
        .getTranslatedPokemon(pokemonName);

    mockMvc
        .perform(get("/pokemon/translated/" + pokemonName))
        .andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(content().json(toJsonString(expectedResponse)));

    verify(pokemonService, times(1)).getTranslatedPokemon(pokemonName);
  }

  @Test
  @DisplayName("Should not be able to get pokemon if pokemon not found")
  void testGetPokemonWithFailure() throws Exception {
    var pokemonName = "mewtwo";

    var expectedResponse = ApiResponse.createErrorResponse(ApiError.fromErrorCode(ErrorCode.P1));

    doThrow(new PokemonNotFoundException(ErrorCode.P1))
        .when(pokemonService)
        .getPokemon(pokemonName);

    mockMvc
        .perform(get("/pokemon/" + pokemonName))
        .andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(content().json(toJsonString(expectedResponse)));

    verify(pokemonService, times(1)).getPokemon(pokemonName);
  }

  private String toJsonString(Object expectedResponse) {
    try {
      return new ObjectMapper().writeValueAsString(expectedResponse);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
