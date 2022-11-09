package com.lschaan.springquartz.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Job already exists.")
public class JobAlreadyExistsException extends RuntimeException {
}
