package es.udc.paproject.backend.model.exceptions;

import java.time.LocalDate;

@SuppressWarnings("serial")
public class InvalidDateException extends Exception {

	private LocalDate date;

	public InvalidDateException(LocalDate date) {
		this.date = date;
	}

	public LocalDate getDate() {
		return date;
	}

}
