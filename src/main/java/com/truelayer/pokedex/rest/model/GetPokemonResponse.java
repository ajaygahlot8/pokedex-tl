package com.truelayer.pokedex.rest.model;

import com.truelayer.pokedex.domain.pokemon.Pokemon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
@Builder
public class GetPokemonResponse {
  @NonNull private final String name;
  @NonNull private final String description;
  @NonNull private final String habitat;
  @NonNull private final Boolean isLegendary;

  public static GetPokemonResponse from(Pokemon pokemon) {
    return GetPokemonResponse.builder()
        .name(pokemon.getName())
        .description(pokemon.getDescription())
        .habitat(pokemon.getHabitat())
        .isLegendary(pokemon.getIsLegendary())
        .build();
  }
}
