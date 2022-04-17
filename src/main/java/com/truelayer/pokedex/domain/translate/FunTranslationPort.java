package com.truelayer.pokedex.domain.translate;

import java.util.Optional;

public interface FunTranslationPort {
  Optional<String> translate(String description, TranslationType translationType);
}
