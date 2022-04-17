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
    var translationType = getTranslationType(pokemon);
    return funTranslationPort.translate(pokemon.getDescription(), translationType);
  }

  private TranslationType getTranslationType(Pokemon pokemon) {
    return pokemon.getHabitat().equals("cave") || pokemon.getIsLegendary()
        ? TranslationType.YODA
        : TranslationType.SHAKESPEARE;
  }
}
