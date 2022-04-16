package com.truelayer.pokedex.domain.pokemon;

import com.truelayer.pokedex.domain.ServiceException;
import com.truelayer.pokedex.exception.ErrorCode;

public class PokemonNotFoundException extends ServiceException {
  public PokemonNotFoundException(ErrorCode errorCode) {
    super(errorCode);
  }
}
