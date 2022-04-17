package com.truelayer.pokedex.adapter;

import com.truelayer.pokedex.domain.translate.FunTranslationPort;
import com.truelayer.pokedex.domain.translate.TranslationType;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FunTranslationAdapter implements FunTranslationPort {

  private final FunTranslationClient funTranslationClient;

  public FunTranslationAdapter(FunTranslationClient funTranslationClient) {
    this.funTranslationClient = funTranslationClient;
  }

  @Override
  public Optional<String> translate(String description, TranslationType translationType) {
    String requestBodyField = "text";
    var translateDescription =
        funTranslationClient.translate(
            translationType.getName(), Map.of(requestBodyField, description));

    try {
      var response = translateDescription.execute();
      var responseBody = Optional.ofNullable(response.body());
      return responseBody.map(
          translationResponse -> translationResponse.getContents().getTranslated());
    } catch (IOException e) {
      log.error("Failed to translate text \n {} \n due to {}", description, e.getMessage());
      return Optional.empty();
    }
  }
}
