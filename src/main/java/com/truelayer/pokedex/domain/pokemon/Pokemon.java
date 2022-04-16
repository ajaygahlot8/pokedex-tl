package com.truelayer.pokedex.domain.pokemon;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pokemon {
  String name;
  String description;
  String habitat;
  Boolean isLegendary;
}
