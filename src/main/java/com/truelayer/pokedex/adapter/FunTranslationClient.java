package com.truelayer.pokedex.adapter;

import java.util.Map;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FunTranslationClient {

  @POST("/translate/{translationType}")
  Call<TranslationResponse> translate(
      @Path("translationType") String translationType, @Body Map<String, Object> body);
}
