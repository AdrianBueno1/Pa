package es.udc.paproject.backend.model.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.paproject.backend.model.entities.Sale;
import es.udc.paproject.backend.model.entities.SaleDao;
import es.udc.paproject.backend.model.entities.User;
import es.udc.paproject.backend.model.entities.UserDao;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.PermissionException;

@Service
@Transactional(readOnly = true)
public class PermissionCheckerImpl implements PermissionChecker {

	@Autowired
	private UserDao userDao;

	@Autowired
	private SaleDao saleDao;

	@Override
	public void checkUserExists(Long userId) throws InstanceNotFoundException {

		if (!userDao.existsById(userId)) {
			throw new InstanceNotFoundException("project.entities.user", userId);
		}

	}

	@Override
	public User checkUser(Long userId) throws InstanceNotFoundException {

		Optional<User> user = userDao.findById(userId);

		if (!user.isPresent()) {
			throw new InstanceNotFoundException("project.entities.user", userId);
		}

		return user.get();

	}

	@Override
	public Sale checksSaleExistsAndBelongsTo(Long saleId, Long userId)
			throws PermissionException, InstanceNotFoundException {

		Optional<Sale> sale = saleDao.findById(saleId);

		if (!sale.isPresent())
			throw new InstanceNotFoundException("project.entities.sale", saleId);

		if (!sale.get().getUser().getId().equals(userId)) {
			throw new PermissionException();
		}

		return sale.get();
	}

}
