package es.udc.paproject.backend.model.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.paproject.backend.model.entities.Sale;
import es.udc.paproject.backend.model.entities.SaleDao;
import es.udc.paproject.backend.model.entities.Session;
import es.udc.paproject.backend.model.entities.SessionDao;
import es.udc.paproject.backend.model.entities.User;
import es.udc.paproject.backend.model.exceptions.AlreadyDeliveredException;
import es.udc.paproject.backend.model.exceptions.IncorrectCreditCardException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.LateForTheSessionException;
import es.udc.paproject.backend.model.exceptions.NotEnoughAvailableSeatsException;

@Service
@Transactional
public class ShoppingServiceImpl implements ShoppingService {
	@Autowired
	private PermissionChecker permissionChecker;

	@Autowired
	private SaleDao saleDao;

	@Autowired
	private SessionDao sessionDao;

	@Override
	public Sale buy(Long userId, Long sessionId, byte seats, String creditcard)
			throws InstanceNotFoundException, LateForTheSessionException, NotEnoughAvailableSeatsException {

		User user = permissionChecker.checkUser(userId);

		Optional<Session> session = sessionDao.findById(sessionId);

		if (!session.isPresent())
			throw new InstanceNotFoundException("project.entities.session", sessionId);

		if (LocalDateTime.now().isAfter(session.get().getDate()))
			throw new LateForTheSessionException(session.get().getDate());

		if (seats > session.get().getAvailableSeats())
			throw new NotEnoughAvailableSeatsException(session.get().getAvailableSeats(), seats);

		Sale sale = new Sale(user, session.get(), seats, creditcard, LocalDateTime.now());
		saleDao.save(sale);

		session.get().setAvailableSeats((short) (session.get().getAvailableSeats() - seats));

		return sale;

	}

	@Override
	@Transactional(readOnly = true)
	public Block<Sale> findSales(Long userId, int page, int size) {

		Slice<Sale> slice = saleDao.findByUserIdOrderByDateDesc(userId, PageRequest.of(page, size));

		return new Block<>(slice.getContent(), slice.hasNext());
	}

	@Override
	public void deliver(Long userId, Long saleId, String creditCard) throws IncorrectCreditCardException,
			InstanceNotFoundException, LateForTheSessionException, AlreadyDeliveredException {

		// Conseguimos la venta por el id
		Optional<Sale> sale = saleDao.findById(saleId);

		// Comprobamos que la sale existe
		if (!sale.isPresent())
			throw new InstanceNotFoundException("project.entities.sale", saleId);

		if (sale.get().isUsed() == true)
			throw new AlreadyDeliveredException();

		// Comprobamos que la tarjeta sea correcta
		if (sale.get().getCreditcard().compareTo(creditCard) != 0)
			throw new IncorrectCreditCardException(creditCard);

		// Comprobamos que la sesión no haya comenzado
		if (LocalDateTime.now().isAfter(sale.get().getSession().getDate()))
			throw new LateForTheSessionException(sale.get().getSession().getDate());

		// Se registra que las entradas quedan entregadas
		sale.get().setUsed(true);

	}

}
