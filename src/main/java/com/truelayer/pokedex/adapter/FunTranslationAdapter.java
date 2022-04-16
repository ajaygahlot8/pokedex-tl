package com.truelayer.pokedex.adapter;

import com.truelayer.pokedex.domain.translate.FunTranslationPort;
import org.springframework.stereotype.Component;

@Component
public class FunTranslationAdapter implements FunTranslationPort {
  @Override
  public String translate(String description) {
    return null;
  }
}
