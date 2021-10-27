package es.udc.paproject.backend.model.exceptions;

@SuppressWarnings("serial")
public class NotEnoughAvailableSeatsException extends Exception {

	private short maxSeats;
	private short requestedSeats;

	public NotEnoughAvailableSeatsException(short maxSeats, short requestedSeats) {
		this.maxSeats = maxSeats;
		this.requestedSeats = requestedSeats;
	}

	public short getMaxSeats() {
		return maxSeats;
	}

	public short getRequestedSeats() {
		return requestedSeats;
	}

}
