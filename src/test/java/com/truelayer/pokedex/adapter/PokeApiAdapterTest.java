package com.truelayer.pokedex.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.truelayer.pokedex.config.Config;
import com.truelayer.pokedex.domain.pokemon.Pokemon;
import com.truelayer.pokedex.domain.translate.TranslationType;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PokeApiAdapterTest {

  MockWebServer server;

  PokeApiAdapter pokeApiAdapter;

  ObjectMapper objectMapper = new Config().configureObjectMapper();

  @BeforeEach
  void setup() throws IOException {
    MockitoAnnotations.openMocks(this);
    server = new MockWebServer();
    server.start();

    var url = "http://" + server.getHostName() + ":" + server.getPort();
    PokeApiClient pokeApiClient =
        new Config().buildRetrofitClient(url, objectMapper, PokeApiClient.class);

    pokeApiAdapter = new PokeApiAdapter(pokeApiClient);
  }

  @Test
  void testGetPokemonDetails() {

    var pokemonName = "mewtwo";
    var description =
        "It was created by a scientist ";
    var pokemon =
        Pokemon.builder()
            .name(pokemonName)
            .description(description)
            .habitat("rare")
            .isLegendary(true)
            .build();


    var response = new MockResponse();
    response.setResponseCode(200);
    response.setBody(
        "{\n" +
            "   \n" +
            "    \"flavor_text_entries\": [\n" +
            "        {\n" +
            "            \"flavor_text\": \"It was created by a scientist \",\n" +
            "            \"language\": {\n" +
            "                \"name\": \"en\",\n" +
            "                \"url\": \"https://pokeapi.co/api/v2/language/9/\"\n" +
            "            },\n" +
            "            \"version\": {\n" +
            "                \"name\": \"red\",\n" +
            "                \"url\": \"https://pokeapi.co/api/v2/version/1/\"\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"flavor_text\": \"ミュウの　遺伝子と　ほとんど\\n同じ。だが　大きさも　性格も\\n恐ろしいほど　違っている。\",\n" +
            "            \"language\": {\n" +
            "                \"name\": \"ja\",\n" +
            "                \"url\": \"https://pokeapi.co/api/v2/language/11/\"\n" +
            "            },\n" +
            "            \"version\": {\n" +
            "                \"name\": \"lets-go-eevee\",\n" +
            "                \"url\": \"https://pokeapi.co/api/v2/version/32/\"\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"flavor_text\": \"超梦的基因几乎和梦幻\\n完全一样，但是大小和\\n性格却迥异得让人吃惊。\",\n" +
            "            \"language\": {\n" +
            "                \"name\": \"zh-Hans\",\n" +
            "                \"url\": \"https://pokeapi.co/api/v2/language/12/\"\n" +
            "            },\n" +
            "            \"version\": {\n" +
            "                \"name\": \"lets-go-eevee\",\n" +
            "                \"url\": \"https://pokeapi.co/api/v2/version/32/\"\n" +
            "            }\n" +
            "        }\n" +
            "    ],\n" +
            "    \"habitat\": {\n" +
            "        \"name\": \"rare\",\n" +
            "        \"url\": \"https://pokeapi.co/api/v2/pokemon-habitat/5/\"\n" +
            "    },\n" +
            "    \"is_legendary\": true\n" +
            "}");

    server.enqueue(response);

    var actual = pokeApiAdapter.getPokemon(pokemonName);
    assertEquals(pokemon, actual.get());
  }

  @Test
  void testGetPokemonDetailsForNon200Status() {
    var pokemonName = "mewtwo";
    var response = new MockResponse();
    response.setResponseCode(500);

    server.enqueue(response);

    var actual = pokeApiAdapter.getPokemon(pokemonName);
    assertTrue(actual.isEmpty());
  }
}
