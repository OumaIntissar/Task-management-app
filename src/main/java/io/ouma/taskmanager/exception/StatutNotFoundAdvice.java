package io.ouma.taskmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class StatutNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(StatutNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String statutNotFoundHandler(StatutNotFoundException ex) {
		log.error(ex.getMessage());
		return ex.getMessage();
	}

}
