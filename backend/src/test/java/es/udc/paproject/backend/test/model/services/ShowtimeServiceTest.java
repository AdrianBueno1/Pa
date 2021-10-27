package es.udc.paproject.backend.test.model.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import es.udc.paproject.backend.model.entities.Session;
import es.udc.paproject.backend.model.entities.SessionDao;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.InvalidDateException;
import es.udc.paproject.backend.model.exceptions.LateForTheSessionException;
import es.udc.paproject.backend.model.services.ShowtimeService;
import es.udc.paproject.backend.model.services.dtos.MovieShowtime;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ShowtimeServiceTest {

	private final Long NON_EXISTENT_ID = new Long(-1);

	@Autowired
	private CityDao cityDao;

	@Autowired
	private CinemaDao cinemaDao;

	@Autowired
	private HallDao hallDao;

	@Autowired
	private SessionDao sessionDao;

	@Autowired
	private MovieDao movieDao;

	@Autowired
	private ShowtimeService movieService;

	private Movie addMovie(String title) {
		return movieDao.save(new Movie(title, "summary", (short) 100));
	}

	private City addCity(String name) {
		return cityDao.save(new City(name));
	}

	private Cinema addCinema(String name, City city) {
		return cinemaDao.save(new Cinema(name, city));
	}

	private Hall addHall(String name, Cinema cinema) {
		return hallDao.save(new Hall(name, cinema, (short) 100));
	}

	private Session addSession(Movie movie, Hall hall, LocalDateTime date) {
		return sessionDao.save(new Session(movie, hall, date, new BigDecimal(10), (short) 100));
	}

	private LocalDateTime getNow() {
		return LocalDateTime.now();
	}

	private LocalDateTime getTomorrow() {
		return LocalDate.now().plusDays(1).atStartOfDay();
	}

	private LocalDateTime getEndOfToday() {
		return LocalDate.now().atTime(23, 59, 59);
	}

	@Test
	public void testFindAllCities() {

		City city1 = addCity("city1");
		City city2 = addCity("city2");

		assertEquals(Arrays.asList(city1, city2), movieService.findAllCities());
	}

	@Test
	public void testFindNoCities() {
		assertEquals(new ArrayList<City>(), movieService.findAllCities());
	}

	@Test
	public void testFindCinemasByCity() {

		City city1 = addCity("city1");
		Cinema cinema1 = addCinema("Cinema1", city1);
		Cinema cinema2 = addCinema("Cinema2", city1);

		assertEquals(Arrays.asList(cinema1, cinema2), movieService.findCinemasByCityId(city1.getId()));
	}

	@Test
	public void testFindCinemasByNonExistentId() {

		City city1 = addCity("city1");
		addCinema("Cinema1", city1);

		assertEquals(new ArrayList<City>(), movieService.findCinemasByCityId(NON_EXISTENT_ID));
	}

	@Test
	public void testFindMovieById() throws InstanceNotFoundException {

		Movie movie = addMovie("movie");

		assertEquals(movie, movieService.findMovieById(movie.getId()));
	}

	@Test
	public void testFindMovieByNonExistentId() {
		assertThrows(InstanceNotFoundException.class, () -> movieService.findMovieById(NON_EXISTENT_ID));
	}

	@Test
	public void testFindSessionById() throws InstanceNotFoundException, LateForTheSessionException {

		City city = addCity("city");
		Cinema cinema = addCinema("cinema", city);
		Hall hall = addHall("hall", cinema);
		Movie movie = addMovie("movie 1");

		Session session = addSession(movie, hall, getNow().plusDays(1));

		assertEquals(session, movieService.findSessionById(session.getId()));
	}

	@Test
	public void testFindSessionByNonExistentId() {
		assertThrows(InstanceNotFoundException.class, () -> movieService.findSessionById(NON_EXISTENT_ID));
	}

	@Test
	public void testFindAlreadyStartedSession() {

		City city = addCity("city");
		Cinema cinema = addCinema("cinema", city);
		Hall hall = addHall("hall", cinema);
		Movie movie = addMovie("movie 1");

		Session session = addSession(movie, hall, getNow().minusDays(1));

		assertThrows(LateForTheSessionException.class, () -> movieService.findSessionById(session.getId()));
	}

	@Test
	public void testFindMovieShowtimesByDafaultDate() throws InvalidDateException {

		City city = addCity("city");
		Cinema cinema = addCinema("cinema", city);
		Hall hall = addHall("hall", cinema);

		Movie movie1 = addMovie("movie 1");
		Movie movie2 = addMovie("movie 2");

		MovieShowtime movieShowtime1 = new MovieShowtime(movie1);
		MovieShowtime movieShowtime2 = new MovieShowtime(movie2);

		addSession(movie2, hall, getNow());
		addSession(movie2, hall, getEndOfToday().plusSeconds(1));

		Session session4 = addSession(movie2, hall, getNow().plusMinutes(30));
		Session session5 = addSession(movie2, hall, getNow().plusHours(1));
		Session session6 = addSession(movie1, hall, getEndOfToday());

		if (session4.getDate().isBefore(getTomorrow())) {
			movieShowtime2.getSessions().add(session4);
		}

		if (session5.getDate().isBefore(getTomorrow())) {
			movieShowtime2.getSessions().add(session5);
		}

		if (session6.getDate().isBefore(getTomorrow())) {
			movieShowtime1.getSessions().add(session6);
		}

		List<MovieShowtime> movieShowtimes = movieService.findMovieShowtimes(cinema.getId(), null);

		ArrayList<MovieShowtime> movieShowtimesList = new ArrayList<MovieShowtime>();

		if (movieShowtime1.getSessions().size() > 0)
			movieShowtimesList.add(movieShowtime1);

		if (movieShowtime2.getSessions().size() > 0)
			movieShowtimesList.add(movieShowtime2);

		assertEquals(movieShowtimesList, movieShowtimes);
	}

	@Test
	public void testFindMovieShowtimesByDate() throws InvalidDateException {

		City city = addCity("city");
		Cinema cinema = addCinema("cinema", city);
		Hall hall = addHall("hall", cinema);

		Movie movie1 = addMovie("movie 1");
		Movie movie2 = addMovie("movie 2");

		MovieShowtime movieShowtime = new MovieShowtime(movie2);

		addSession(movie1, hall, getNow());
		addSession(movie1, hall, getEndOfToday());

		Session session3 = addSession(movie2, hall, getNow().plusDays(1).plusHours(1));

		if (session3.getDate().isBefore(getTomorrow().plusDays(1))) {
			movieShowtime.getSessions().add(session3);
		}

		List<MovieShowtime> movieShowtimes = movieService.findMovieShowtimes(cinema.getId(),
				LocalDate.now().plusDays(1));

		ArrayList<MovieShowtime> movieShowtimesList = new ArrayList<MovieShowtime>();

		if (movieShowtime.getSessions().size() > 0)
			movieShowtimesList.add(movieShowtime);

		assertEquals(movieShowtimesList, movieShowtimes);
	}

	@Test
	public void testFindNoMovieShowtimes() throws InvalidDateException {

		City city = addCity("city");
		Cinema cinema = addCinema("cinema", city);
		Hall hall = addHall("hall", cinema);

		Movie movie1 = addMovie("movie 1");
		addSession(movie1, hall, getNow());

		List<MovieShowtime> movieShowtimes = movieService.findMovieShowtimes(cinema.getId(),
				LocalDate.now().plusDays(1));

		assertEquals(new ArrayList<MovieShowtime>(), movieShowtimes);
	}

	@Test
	public void testFindNoMovieShowtimesWithIncorrectCinemaId() throws InvalidDateException {

		City city = addCity("city");
		Cinema cinema = addCinema("cinema", city);
		Hall hall = addHall("hall", cinema);

		Movie movie1 = addMovie("movie 1");

		addSession(movie1, hall, getNow());

		List<MovieShowtime> movieShowtimes = movieService.findMovieShowtimes(NON_EXISTENT_ID, null);

		assertEquals(new ArrayList<MovieShowtime>(), movieShowtimes);
	}

	@Test
	public void testFindMovieShowtimesWithCorrectOrder() throws InvalidDateException {

		City city = addCity("city");
		Cinema cinema = addCinema("cinema", city);
		Hall hall = addHall("hall", cinema);

		Movie movie1 = addMovie("movie 1");
		Movie movie2 = addMovie("movie 2");

		MovieShowtime movieShowtime1 = new MovieShowtime(movie1);
		MovieShowtime movieShowtime2 = new MovieShowtime(movie2);

		Session session1 = addSession(movie2, hall, getNow().plusHours(4));
		Session session2 = addSession(movie1, hall, getNow().plusMinutes(30));
		Session session3 = addSession(movie2, hall, getNow().plusHours(2));

		if (session2.getDate().isBefore(getTomorrow())) {
			movieShowtime1.getSessions().add(session2);
		}

		if (session3.getDate().isBefore(getTomorrow())) {
			movieShowtime2.getSessions().add(session3);
		}

		if (session1.getDate().isBefore(getTomorrow())) {
			movieShowtime2.getSessions().add(session1);
		}

		List<MovieShowtime> movieShowtimes = movieService.findMovieShowtimes(cinema.getId(), null);

		ArrayList<MovieShowtime> movieShowtimesList = new ArrayList<MovieShowtime>();

		if (movieShowtime1.getSessions().size() > 0)
			movieShowtimesList.add(movieShowtime1);

		if (movieShowtime2.getSessions().size() > 0)
			movieShowtimesList.add(movieShowtime2);

		assertEquals(movieShowtimesList, movieShowtimes);
	}

	@Test
	public void testFindMovieShowtimesBeforeToday() {

		City city = addCity("city");
		Cinema cinema = addCinema("cinema", city);

		assertThrows(InvalidDateException.class,
				() -> movieService.findMovieShowtimes(cinema.getId(), LocalDate.now().minusDays(1)));
	}

	@Test
	public void testFindMovieShowtimesAfterSixDays() {

		City city = addCity("city");
		Cinema cinema = addCinema("cinema", city);

		assertThrows(InvalidDateException.class,
				() -> movieService.findMovieShowtimes(cinema.getId(), LocalDate.now().plusDays(7)));
	}
}
