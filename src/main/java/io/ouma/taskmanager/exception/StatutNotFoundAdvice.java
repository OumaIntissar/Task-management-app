package io.ouma.taskmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class StatutNotFoundAdvice {

	@ResponseBody
	  @ExceptionHandler(StatutNotFoundException.class)
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  String statutNotFoundHandler(StatutNotFoundException ex) {
	    return ex.getMessage();
	  }
	
}
