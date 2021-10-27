package es.udc.paproject.backend.rest.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class BuyParamsDto {

	private byte seats;
	private String creditCard;

	BuyParamsDto() {
	}

	@NotNull
	@Range(min = 1, max = 10)
	public byte getSeats() {
		return seats;
	}

	public void setSeats(byte seats) {
		this.seats = seats;
	}

	@NotNull
	@Size(min = 16, max = 16)
	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

}
