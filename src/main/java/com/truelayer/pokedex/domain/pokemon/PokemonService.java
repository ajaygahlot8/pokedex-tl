package com.truelayer.pokedex.domain.pokemon;

import com.truelayer.pokedex.domain.translate.TranslationService;
import com.truelayer.pokedex.exception.ErrorCode;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {

  private final PokeApiPort pokeApiPort;

  private final TranslationService translationService;

  public PokemonService(PokeApiPort pokeApiPort, TranslationService translationService) {
    this.pokeApiPort = pokeApiPort;
    this.translationService = translationService;
  }

  public Pokemon getPokemon(String pokemonName) {
    return pokeApiPort
        .getPokemon(pokemonName)
        .orElseThrow(() -> new PokemonNotFoundException(ErrorCode.P1));
  }

  public Pokemon getTranslatedPokemon(String pokemonName) {
    var pokemon = getPokemon(pokemonName);
    var translatedDescription = translationService.translateDescription(pokemon);
    pokemon.setDescription(translatedDescription);
    return pokemon;
  }
}
