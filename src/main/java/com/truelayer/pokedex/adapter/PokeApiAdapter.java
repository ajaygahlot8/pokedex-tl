package com.truelayer.pokedex.adapter;

import com.truelayer.pokedex.domain.pokemon.PokeApiPort;
import com.truelayer.pokedex.domain.pokemon.Pokemon;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PokeApiAdapter implements PokeApiPort {

  private final PokeApiClient pokeApiClient;
  private static final String LANGUAGE_ENGLISH = "en";

  public PokeApiAdapter(PokeApiClient pokeApiClient) {
    this.pokeApiClient = pokeApiClient;
  }

  @Override
  public Optional<Pokemon> getPokemon(String pokemonName) {
    var pokemonDetails = pokeApiClient.getPokemonSpeciesDetails(pokemonName);
    try {
      var response = pokemonDetails.execute();
      var responseBody = Optional.ofNullable(response.body());
      return responseBody.flatMap(pokeApiResponse -> preparePokemon(pokeApiResponse, pokemonName));
    } catch (IOException e) {
      log.error(
          "Failed to get Pokemon Species Details for pokemon {} due to {}",
          pokemonName,
          e.getMessage());
      return Optional.empty();
    }
  }

  private Optional<Pokemon> preparePokemon(PokeApiResponse response, String pokemonName) {

    return Optional.of(
        Pokemon.builder()
            .name(pokemonName)
            .habitat(response.getHabitat().getName())
            .isLegendary(response.getIsLegendary())
            .description(prepareDescription(response.getFlavorTexts()))
            .build());
  }

  private String prepareDescription(List<PokeApiResponse.FlavorText> flavorTexts) {
    return flavorTexts.stream()
        .filter(flavorText -> flavorText.getLanguage().getName().equals(LANGUAGE_ENGLISH))
        .findAny()
        .orElse(PokeApiResponse.FlavorText.builder().flavorText("").build())
        .getFlavorText();
  }
}
