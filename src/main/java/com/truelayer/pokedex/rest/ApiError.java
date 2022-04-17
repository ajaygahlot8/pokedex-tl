package com.truelayer.pokedex.rest;

import com.truelayer.pokedex.exception.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode
public class ApiError {

  @NonNull private final String code;
  @NonNull private final String message;

  public static ApiError fromErrorCode(ErrorCode errorCode) {
    return new ApiError(errorCode.getCode(), errorCode.getMessage());
  }
}
