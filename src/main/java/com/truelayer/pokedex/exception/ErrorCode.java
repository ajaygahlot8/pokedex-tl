package com.truelayer.pokedex.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
  E1("Internal Server Error"),
  P1("Pokemon not found");
  private final String message;
  private final String code;

  ErrorCode(String message) {
    this.message = message;
    this.code = this.name();
  }
}
