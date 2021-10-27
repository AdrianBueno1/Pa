package es.udc.paproject.backend.test.model.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import es.udc.paproject.backend.model.entities.Cinema;
import es.udc.paproject.backend.model.entities.City;
import es.udc.paproject.backend.model.entities.Hall;
import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.Sale;
import es.udc.paproject.backend.model.entities.Session;
import es.udc.paproject.backend.model.entities.User;
import es.udc.paproject.backend.model.entities.User.RoleType;

public class SaleTest {

	private final String VALID_CREDIT_CARD = "1616161616161616";

	private User createUser(String userName, RoleType roleType) {
		return new User(userName, "password", "firstName", "lastName", userName + "@" + userName + ".com", roleType);
	}

	private Sale createSale(User user, Session session, byte seats, String creditcard, LocalDateTime date) {
		return new Sale(user, session, seats, creditcard, date);
	}

	private Session createSession(Movie movie, Hall hall, LocalDateTime date) {
		return new Session(movie, hall, date, new BigDecimal(10));
	}

	private Movie createMovie(String name) {
		return new Movie(name, "nada", (short) 160.00);
	}

	private Hall createHall(String name, Cinema cinema, short capacity) {
		return new Hall(name, cinema, capacity);
	}

	private Cinema createCinema(String name, City city) {
		return new Cinema(name, city);
	}

	private City createCity(String name) {
		return new City(name);
	}

	private LocalDateTime getNow() {
		return LocalDateTime.now();
	}

	@Test
	public void testGetTotalPrice() {

		byte seatsToBuy = 10;

		User user = createUser("Pedro", RoleType.USER);
		City city = createCity("Pontevedra");
		Cinema cinema = createCinema("Cines", city);
		Movie movie = createMovie("Shrek");
		Hall hall = createHall("Sala 1", cinema, (short) 50);
		Session session = createSession(movie, hall, getNow().plusHours(1));

		Sale sale = createSale(user, session, seatsToBuy, VALID_CREDIT_CARD, getNow());

		BigDecimal totalPrice = session.getPrice().multiply(new BigDecimal(seatsToBuy));

		assertEquals(totalPrice, sale.getTotalPrice());

	}

}
