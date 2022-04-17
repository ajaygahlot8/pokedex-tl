package com.truelayer.pokedex.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class TranslationResponse {

  Content contents;

  public static class Content {
    @Getter public String translated;
  }
}
