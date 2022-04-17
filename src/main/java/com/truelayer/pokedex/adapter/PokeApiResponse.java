package com.truelayer.pokedex.adapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class PokeApiResponse {

  Habitat habitat;

  @JsonProperty("is_legendary")
  Boolean isLegendary;

  @JsonProperty("flavor_text_entries")
  List<FlavorText> flavorTexts;

  public static class Habitat {
    @Getter public String name;
  }

  public static class Language {
    @Getter public String name;
  }

  @Getter
  @Data
  @Builder
  public static class FlavorText {
    @JsonProperty("flavor_text")
    public String flavorText;

    public Language language;
  }
}
