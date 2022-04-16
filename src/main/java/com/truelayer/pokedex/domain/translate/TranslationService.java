package com.truelayer.pokedex.domain.translate;

import com.truelayer.pokedex.domain.pokemon.Pokemon;
import org.springframework.stereotype.Service;

@Service
public class TranslationService {

  private final FunTranslationPort funTranslationPort;

  public TranslationService(FunTranslationPort funTranslationPort) {
    this.funTranslationPort = funTranslationPort;
  }

  public String translateDescription(Pokemon pokemon) {
    return funTranslationPort.translate(pokemon.getDescription());
  }
}
