package es.udc.paproject.backend.model.exceptions;

@SuppressWarnings("serial")
public class IncorrectCreditCardException extends Exception {

	private String creditCard;

	public IncorrectCreditCardException(String creditCard) {
		this.creditCard = creditCard;
	}

	public String getCreditCard() {
		return creditCard;
	}

}
