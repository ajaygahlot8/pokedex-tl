package com.truelayer.pokedex.domain.pokemon;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

@Data
@Builder
public class Pokemon {
  @NonNull private final String name;
  @Setter @NonNull private String description;
  @NonNull private final String habitat;
  @NonNull private final Boolean isLegendary;
}
