package com.truelayer.pokedex.rest;

import com.truelayer.pokedex.domain.ServiceException;
import com.truelayer.pokedex.domain.pokemon.PokemonNotFoundException;
import com.truelayer.pokedex.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {

  @ExceptionHandler(value = ServiceException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @RequestMapping(produces = "application/json")
  private ApiResponse<Void> borrowBookException(ServiceException exception) {
    log.error("Handling ServiceException", exception);
    return ApiResponse.createErrorResponse(ApiError.fromErrorCode(exception.getError()));
  }

  @ExceptionHandler(value = Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @RequestMapping(produces = "application/json")
  private ApiResponse<Void> unknownException(Exception exception) {
    log.error("Handling UnknownException", exception);
    return ApiResponse.createErrorResponse(ApiError.fromErrorCode(ErrorCode.E1));
  }

  @ExceptionHandler(value = PokemonNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  ApiResponse<Void> handlePolicyNotFoundException(PokemonNotFoundException ex) {
    log.error("Pokemon details not found");
    return ApiResponse.createErrorResponse(ApiError.fromErrorCode(ex.getError()));
  }
}
