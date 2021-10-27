package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.Sale;
import es.udc.paproject.backend.model.exceptions.AlreadyDeliveredException;
import es.udc.paproject.backend.model.exceptions.IncorrectCreditCardException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.LateForTheSessionException;
import es.udc.paproject.backend.model.exceptions.NotEnoughAvailableSeatsException;

public interface ShoppingService {

	public Sale buy(Long userId, Long sessionId, byte seats, String creditcard)
			throws InstanceNotFoundException, LateForTheSessionException, NotEnoughAvailableSeatsException;

	public Block<Sale> findSales(Long userId, int page, int size);

	public void deliver(Long userId, Long saleId, String creditCard) throws IncorrectCreditCardException,
			InstanceNotFoundException, LateForTheSessionException, AlreadyDeliveredException;

}
