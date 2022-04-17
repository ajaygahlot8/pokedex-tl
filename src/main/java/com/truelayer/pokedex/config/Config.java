package com.truelayer.pokedex.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.truelayer.pokedex.adapter.FunTranslationClient;
import com.truelayer.pokedex.adapter.PokeApiClient;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class Config {

  @Bean
  public ObjectMapper configureObjectMapper() {
    var mapper = new ObjectMapper();
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    return mapper;
  }

  @Bean
  public PokeApiClient configurePokeApiClient(ObjectMapper objectMapper) {
    return buildRetrofitClient("https://pokeapi.co/", objectMapper, PokeApiClient.class);
  }

  @Bean
  public FunTranslationClient configureFunTranslationClient(ObjectMapper objectMapper) {
    return buildRetrofitClient(
        "https://api.funtranslations.com/", objectMapper, FunTranslationClient.class);
  }

  public <T> T buildRetrofitClient(String hostname, ObjectMapper objectMapper, Class<T> clazz) {
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    Retrofit retrofit =
        new Retrofit.Builder()
            .baseUrl(hostname)
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .client(httpClient.build())
            .build();
    return retrofit.create(clazz);
  }
}
