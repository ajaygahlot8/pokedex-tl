package com.truelayer.pokedex.rest.controller;

import com.truelayer.pokedex.domain.pokemon.PokemonService;
import com.truelayer.pokedex.rest.ApiResponse;
import com.truelayer.pokedex.rest.model.GetPokemonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PokemonController {

  private final PokemonService pokemonService;

  public PokemonController(PokemonService pokemonService) {
    this.pokemonService = pokemonService;
  }

  @GetMapping("/pokemon/{pokemonName}")
  public ApiResponse<GetPokemonResponse> getPokemon(
      @PathVariable("pokemonName") String pokemonName) {

    var pokemon = pokemonService.getPokemon(pokemonName);

    return ApiResponse.createSuccessResponse(GetPokemonResponse.from(pokemon));
  }
}
