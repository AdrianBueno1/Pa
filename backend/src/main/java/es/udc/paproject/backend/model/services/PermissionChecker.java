package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.Sale;
import es.udc.paproject.backend.model.entities.User;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.PermissionException;

public interface PermissionChecker {

	public void checkUserExists(Long userId) throws InstanceNotFoundException;

	public User checkUser(Long userId) throws InstanceNotFoundException;

	public Sale checksSaleExistsAndBelongsTo(Long saleId, Long userId)
			throws PermissionException, InstanceNotFoundException;

}
