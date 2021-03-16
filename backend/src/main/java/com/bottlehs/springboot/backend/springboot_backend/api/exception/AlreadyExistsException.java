package com.bottlehs.springboot.backend.springboot_backend.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author bottlehs
 * @since 2021.02.04
 */
@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Already exists")
public class AlreadyExistsException extends RuntimeException {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public AlreadyExistsException(String message) {
    super(message);
  }
}
