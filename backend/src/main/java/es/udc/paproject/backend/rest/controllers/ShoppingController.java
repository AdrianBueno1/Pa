package es.udc.paproject.backend.rest.controllers;

import static es.udc.paproject.backend.rest.dtos.SaleConversor.toSaleDtos;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.udc.paproject.backend.model.entities.Sale;
import es.udc.paproject.backend.model.exceptions.AlreadyDeliveredException;
import es.udc.paproject.backend.model.exceptions.IncorrectCreditCardException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.LateForTheSessionException;
import es.udc.paproject.backend.model.exceptions.NotEnoughAvailableSeatsException;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.ShoppingService;
import es.udc.paproject.backend.rest.common.ErrorsDto;
import es.udc.paproject.backend.rest.dtos.BlockDto;
import es.udc.paproject.backend.rest.dtos.BuyParamsDto;
import es.udc.paproject.backend.rest.dtos.DeliverParamsDto;
import es.udc.paproject.backend.rest.dtos.IdDto;
import es.udc.paproject.backend.rest.dtos.SaleDto;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {

	private final static String INCORRECT_CREDIT_CARD_EXCEPTION_CODE = "project.exceptions.IncorrectCreditCardException";
	private final static String ALREADY_DELIVERED_EXCEPTION_CODE = "project.exceptions.AlreadyDeliveredException";
	private final static String NOT_ENOUGH_AVAILABLE_SEATS_EXCEPTION_CODE = "project.exceptions.NotEnoughAvailableSeatsException";

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ShoppingService shoppingService;

	@ExceptionHandler(IncorrectCreditCardException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorsDto handleIncorrectCreditCardException(IncorrectCreditCardException exception, Locale locale) {

		String nameMessage = messageSource.getMessage(exception.getCreditCard(), null, exception.getCreditCard(),
				locale);
		String errorMessage = messageSource.getMessage(INCORRECT_CREDIT_CARD_EXCEPTION_CODE,
				new Object[] { nameMessage }, INCORRECT_CREDIT_CARD_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);

	}

	@ExceptionHandler(AlreadyDeliveredException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ResponseBody
	public ErrorsDto handleAlreadyDeilveredException(AlreadyDeliveredException exception, Locale locale) {

		String errorMessage = messageSource.getMessage(ALREADY_DELIVERED_EXCEPTION_CODE, null,
				ALREADY_DELIVERED_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);

	}

	@ExceptionHandler(NotEnoughAvailableSeatsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorsDto handleNotEnoughAvailableSeatsException(NotEnoughAvailableSeatsException exception, Locale locale) {

		String errorMessage = messageSource.getMessage(NOT_ENOUGH_AVAILABLE_SEATS_EXCEPTION_CODE, null,
				NOT_ENOUGH_AVAILABLE_SEATS_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);

	}

	@PostMapping("/sessions/{sessionId}/buy")
	public IdDto buy(@RequestAttribute Long userId, @PathVariable Long sessionId,
			@Validated @RequestBody BuyParamsDto params)
			throws InstanceNotFoundException, LateForTheSessionException, NotEnoughAvailableSeatsException {

		return new IdDto(shoppingService.buy(userId, sessionId, params.getSeats(), params.getCreditCard()).getId());

	}

	@GetMapping("/sales")
	public BlockDto<SaleDto> findSales(@RequestAttribute Long userId, @RequestParam(defaultValue = "0") int page) {

		Block<Sale> saleBlock = shoppingService.findSales(userId, page, 10);

		return new BlockDto<>(toSaleDtos(saleBlock.getItems()), saleBlock.getExistMoreItems());

	}

	@PostMapping("/sales/{saleId}/deliverTickets")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deliver(@RequestAttribute Long userId, @PathVariable Long saleId,
			@Validated @RequestBody DeliverParamsDto params) throws IncorrectCreditCardException,
			InstanceNotFoundException, LateForTheSessionException, AlreadyDeliveredException {

		shoppingService.deliver(userId, saleId, params.getCreditCard());
	}

}