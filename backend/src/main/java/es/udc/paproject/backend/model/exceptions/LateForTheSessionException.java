package es.udc.paproject.backend.model.exceptions;

import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class LateForTheSessionException extends Exception {

	private LocalDateTime date;

	public LateForTheSessionException(LocalDateTime date) {
		this.date = date;
	}

	public LocalDateTime getDate() {
		return date;
	}

}