package io.ouma.taskmanager.exception;

public class StatutNotFoundException extends RuntimeException{

	public StatutNotFoundException(Long id) {
	    super("Could not find statut with id = " + id + " ! ");
	  }
}
