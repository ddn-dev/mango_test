package com.mango.customer.exception;

import com.mango.customer.constant.ExceptionMessageConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class MaxSloganLimitReachedException extends RuntimeException {

  public MaxSloganLimitReachedException() {
    super(ExceptionMessageConstants.MAX_SLOGANS_LIMIT_REACHED);
  }
}
