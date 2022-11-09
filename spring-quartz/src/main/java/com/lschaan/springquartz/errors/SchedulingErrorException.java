package com.lschaan.springquartz.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Unable to schedule the job.")
public class SchedulingErrorException extends RuntimeException {
}
