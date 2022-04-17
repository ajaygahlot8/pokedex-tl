package com.truelayer.pokedex.adapter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokeApiClient {

  @GET("/api/v2/pokemon-species/{pokemonName}")
  Call<PokeApiResponse> getPokemonSpeciesDetails(@Path("pokemonName") String pokemonName);
}
