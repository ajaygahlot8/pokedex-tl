package com.truelayer.pokedex.domain.translate;

import lombok.Getter;

@Getter
public enum TranslationType {
  YODA("yoda"),
  SHAKESPEARE("shakespeare");

  private final String name;

  TranslationType(String name) {
    this.name = name;
  }
}
