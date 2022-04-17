package com.truelayer.pokedex.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.truelayer.pokedex.config.Config;
import com.truelayer.pokedex.domain.translate.TranslationType;
import java.io.IOException;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

class FunTranslationAdapterTest {

  MockWebServer server;

  FunTranslationAdapter funTranslationAdapter;

  ObjectMapper objectMapper = new Config().configureObjectMapper();

  @BeforeEach
  void setup() throws IOException {
    MockitoAnnotations.openMocks(this);
    server = new MockWebServer();
    server.start();

    var url = "http://" + server.getHostName() + ":" + server.getPort();
    FunTranslationClient funTranslationClient =
        new Config().buildRetrofitClient(url, objectMapper, FunTranslationClient.class);

    funTranslationAdapter = new FunTranslationAdapter(funTranslationClient);
  }

  @Test
  void testTranslateDescriptionForYoda() {

    var response = new MockResponse();
    response.setResponseCode(200);
    response.setBody(
        "{\n"
            + "  \"success\": {\n"
            + "    \"total\": 1\n"
            + "  },\n"
            + "  \"contents\": {\n"
            + "    \"translated\": \"a description this is\",\n"
            + "    \"text\": \"this is a description\",\n"
            + "    \"translation\": \"yoda\"\n"
            + "  }\n"
            + "}");

    server.enqueue(response);

    var description = "this is a description";
    var expected = "a description this is";

    var actual = funTranslationAdapter.translate(description, TranslationType.YODA);
    assertEquals(expected, actual.get());
  }

  @Test
  void testTranslateDescriptionForShakespeare() {

    var response = new MockResponse();
    response.setResponseCode(200);
    response.setBody(
        "{\n"
            + "  \"success\": {\n"
            + "    \"total\": 1\n"
            + "  },\n"
            + "  \"contents\": {\n"
            + "    \"translated\": \"a description this is\",\n"
            + "    \"text\": \"this is a description\",\n"
            + "    \"translation\": \"shakespeare\"\n"
            + "  }\n"
            + "}");

    server.enqueue(response);

    var description = "this is a description";
    var expected = "a description this is";

    var actual = funTranslationAdapter.translate(description, TranslationType.SHAKESPEARE);
    assertEquals(expected, actual.get());
  }

  @Test
  void testTranslateDescriptionForNon200Status() {

    var response = new MockResponse();
    response.setResponseCode(500);

    server.enqueue(response);

    var description = "this is a description";

    var actual = funTranslationAdapter.translate(description, TranslationType.SHAKESPEARE);
    assertTrue(actual.isEmpty());
  }
}
