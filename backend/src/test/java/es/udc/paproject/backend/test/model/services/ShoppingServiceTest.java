package es.udc.paproject.backend.test.model.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import es.udc.paproject.backend.model.entities.Cinema;
import es.udc.paproject.backend.model.entities.CinemaDao;
import es.udc.paproject.backend.model.entities.City;
import es.udc.paproject.backend.model.entities.CityDao;
import es.udc.paproject.backend.model.entities.Hall;
import es.udc.paproject.backend.model.entities.HallDao;
import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.MovieDao;
import es.udc.paproject.backend.model.entities.Sale;
import es.udc.paproject.backend.model.entities.SaleDao;
import es.udc.paproject.backend.model.entities.Session;
import es.udc.paproject.backend.model.entities.SessionDao;
import es.udc.paproject.backend.model.entities.User;
import es.udc.paproject.backend.model.entities.User.RoleType;
import es.udc.paproject.backend.model.exceptions.AlreadyDeliveredException;
import es.udc.paproject.backend.model.exceptions.DuplicateInstanceException;
import es.udc.paproject.backend.model.exceptions.IncorrectCreditCardException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.LateForTheSessionException;
import es.udc.paproject.backend.model.exceptions.NotEnoughAvailableSeatsException;
import es.udc.paproject.backend.model.exceptions.PermissionException;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.ShoppingService;
import es.udc.paproject.backend.model.services.UserService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ShoppingServiceTest {

	private final Long NON_EXISTENT_ID = new Long(-1);
	private final String VALID_CREDIT_CARD = "1616161616161616";
	private final String INVALID_CREDIT_CARD = "0000000000000000";

	@Autowired
	private UserService userService;

	@Autowired
	private SaleDao saleDao;

	@Autowired
	private SessionDao sessionDao;

	@Autowired
	private MovieDao movieDao;

	@Autowired
	private HallDao hallDao;

	@Autowired
	private CinemaDao cinemaDao;

	@Autowired
	private CityDao cityDao;

	@Autowired
	private ShoppingService shoppingService;

	private User signUpUser(String userName, RoleType roleType) {

		User user = new User(userName, "password", "firstName", "lastName", userName + "@" + userName + ".com",
				roleType);

		try {
			userService.signUp(user);
		} catch (DuplicateInstanceException e) {
			throw new RuntimeException(e);
		}

		return user;

	}

	private Sale createSale(User user, Session session, byte seats, String creditcard, LocalDateTime date) {
		return saleDao.save(new Sale(user, session, seats, creditcard, date));
	}

	private Session createSession(Movie movie, Hall hall, LocalDateTime date) {
		return sessionDao.save(new Session(movie, hall, date, new BigDecimal(10)));
	}

	private Movie createMovie(String name) {
		return movieDao.save(new Movie(name, "nada", (short) 160.00));
	}

	private Hall createHall(String name, Cinema cinema, short capacity) {
		return hallDao.save(new Hall(name, cinema, capacity));
	}

	private Cinema createCinema(String name, City city) {
		return cinemaDao.save(new Cinema(name, city));
	}

	private City createCity(String name) {
		return cityDao.save(new City(name));
	}

	private LocalDateTime getNow() {
		return LocalDateTime.now();
	}

	private LocalDateTime getTomorrow() {
		return LocalDate.now().plusDays(1).atStartOfDay();
	}

	@Test
	public void testDeliverTicketsWithInvalidSaleId() throws InstanceNotFoundException {

		User taquillero = signUpUser("Manolo", RoleType.TICKET_SELLER);

		assertThrows(InstanceNotFoundException.class,
				() -> shoppingService.deliver(taquillero.getId(), NON_EXISTENT_ID, VALID_CREDIT_CARD));
	}

	@Test
	public void testDeliverTicketsAlreadyDelivered() throws AlreadyDeliveredException, IncorrectCreditCardException,
			InstanceNotFoundException, LateForTheSessionException {

		User user = signUpUser("Pedro", RoleType.USER);
		User taquillero = signUpUser("Manolo", RoleType.TICKET_SELLER);

		City ciudad = createCity("Pontevedra");
		Cinema cine = createCinema("Cines", ciudad);
		Hall sala = createHall("Sala 1", cine, (short) 60);
		Movie peli = createMovie("Shrek");

		Session sesion = createSession(peli, sala, getTomorrow());
		Sale sale = createSale(user, sesion, (byte) 2, VALID_CREDIT_CARD, getNow());
		sale.setUsed(true);

		assertThrows(AlreadyDeliveredException.class,
				() -> shoppingService.deliver(taquillero.getId(), sale.getId(), VALID_CREDIT_CARD));
	}

	@Test
	public void testDeliverTicketsWithIncorrectCreditCard() throws IncorrectCreditCardException {

		User user = signUpUser("Pedro", RoleType.USER);
		User taquillero = signUpUser("Manolo", RoleType.TICKET_SELLER);

		City ciudad = createCity("Pontevedra");
		Cinema cine = createCinema("Cines", ciudad);
		Hall sala = createHall("Sala 1", cine, (short) 60);
		Movie peli = createMovie("Shrek");

		Session sesion = createSession(peli, sala, getTomorrow());
		Sale sale = createSale(user, sesion, (byte) 2, VALID_CREDIT_CARD, getNow());

		assertThrows(IncorrectCreditCardException.class,
				() -> shoppingService.deliver(taquillero.getId(), sale.getId(), INVALID_CREDIT_CARD));

		assertEquals(false, sale.isUsed());
	}

	@Test
	public void testDeliverTicketsOfAStartedSession() throws LateForTheSessionException {

		User user = signUpUser("Pedro", RoleType.USER);
		User taquillero = signUpUser("Manolo", RoleType.TICKET_SELLER);

		City ciudad = createCity("Pontevedra");
		Cinema cine = createCinema("Cines", ciudad);
		Hall sala = createHall("Sala 1", cine, (short) 60);
		Movie peli = createMovie("Shrek");

		Session sesion = createSession(peli, sala, getNow().minusHours(1));
		Sale sale = createSale(user, sesion, (byte) 2, VALID_CREDIT_CARD, getNow().minusDays(1));

		assertThrows(LateForTheSessionException.class,
				() -> shoppingService.deliver(taquillero.getId(), sale.getId(), VALID_CREDIT_CARD));

		assertEquals(false, sale.isUsed());
	}

	@Test
	public void testDeliverTickets() throws IncorrectCreditCardException, InstanceNotFoundException,
			LateForTheSessionException, AlreadyDeliveredException {

		User user = signUpUser("Pedro", RoleType.USER);
		User taquillero = signUpUser("Manolo", RoleType.TICKET_SELLER);

		City ciudad = createCity("Pontevedra");
		Cinema cine = createCinema("Cines", ciudad);
		Hall sala = createHall("Sala 1", cine, (short) 60);
		Movie peli = createMovie("Shrek");

		Session sesion = createSession(peli, sala, getTomorrow());
		Sale sale = createSale(user, sesion, (byte) 2, VALID_CREDIT_CARD, getNow());

		shoppingService.deliver(taquillero.getId(), sale.getId(), VALID_CREDIT_CARD);

		assertEquals(true, sale.isUsed());

	}

	@Test
	public void testBuyAndFindSale() throws InstanceNotFoundException, PermissionException, LateForTheSessionException,
			NotEnoughAvailableSeatsException {
		byte seats = 5;

		User user = signUpUser("Pedro", RoleType.USER);

		City city = createCity("Pontevedra");
		Cinema cinema = createCinema("Cines", city);
		Movie movie = createMovie("Shrek");

		Hall hall = createHall("Sala 1", cinema, (short) 10);
		Session session = createSession(movie, hall, getNow().plusHours(1));

		Sale sale = shoppingService.buy(user.getId(), session.getId(), seats, VALID_CREDIT_CARD);

		assertEquals(user, sale.getUser());
		assertEquals(seats, sale.getSeats());
		assertEquals(VALID_CREDIT_CARD, sale.getCreditcard());
		assertEquals(session.getPrice().multiply(new BigDecimal(seats)), sale.getTotalPrice());

		assertEquals(hall.getCapacity() - seats, sale.getSession().getAvailableSeats());
		assertEquals(false, sale.isUsed());

	}

	@Test
	public void testBuyWithInvalidSessionId() {

		User user = signUpUser("Pedro", RoleType.USER);

		assertThrows(InstanceNotFoundException.class,
				() -> shoppingService.buy(user.getId(), NON_EXISTENT_ID, (byte) 1, VALID_CREDIT_CARD));

	}

	@Test
	public void testBuyTicketsOfAStartedSession() {

		User user = signUpUser("Pedro", RoleType.USER);

		City city = createCity("Pontevedra");
		Cinema cinema = createCinema("Cines", city);
		Hall hall = createHall("Sala 1", cinema, (short) 60);
		Movie movie = createMovie("Shrek");

		Session session = createSession(movie, hall, getNow().minusHours(1));

		assertThrows(LateForTheSessionException.class,
				() -> shoppingService.buy(user.getId(), session.getId(), (byte) 1, VALID_CREDIT_CARD));

	}

	@Test
	public void testBuyTooManySeats() {

		User user = signUpUser("Pedro", RoleType.USER);

		City city = createCity("Pontevedra");
		Cinema cinema = createCinema("Cines", city);
		Movie movie = createMovie("Shrek");

		Hall hall = createHall("Sala 1", cinema, (short) 5);
		Session session = createSession(movie, hall, getNow().plusHours(1));

		assertThrows(NotEnoughAvailableSeatsException.class,
				() -> shoppingService.buy(user.getId(), session.getId(), (byte) 6, VALID_CREDIT_CARD));

	}

	@Test
	public void testFindSales() {

		User user = signUpUser("Pedro", RoleType.USER);

		City city = createCity("Pontevedra");
		Cinema cinema = createCinema("Cines", city);
		Movie movie = createMovie("Shrek");

		Hall hall = createHall("Sala 1", cinema, (short) 15);
		Session session = createSession(movie, hall, getNow().plusDays(5));

		Sale sale1 = createSale(user, session, (byte) 5, VALID_CREDIT_CARD, LocalDateTime.now().minusDays(2));
		Sale sale2 = createSale(user, session, (byte) 5, VALID_CREDIT_CARD, LocalDateTime.now().minusDays(1));
		Sale sale3 = createSale(user, session, (byte) 5, VALID_CREDIT_CARD, LocalDateTime.now());

		Block<Sale> expectedSales = new Block<>(Arrays.asList(sale3, sale2), true);
		assertEquals(expectedSales, shoppingService.findSales(user.getId(), 0, 2));

		expectedSales = new Block<>(Arrays.asList(sale1), false);
		assertEquals(expectedSales, shoppingService.findSales(user.getId(), 1, 2));

	}

	@Test
	public void testFindNoSales() {

		User user = signUpUser("Pedro", RoleType.USER);

		Block<Sale> expectedSales = new Block<>(new ArrayList<>(), false);

		assertEquals(expectedSales, shoppingService.findSales(user.getId(), 0, 1));

	}

}