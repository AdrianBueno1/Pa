package es.udc.paproject.backend.model.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.paproject.backend.model.entities.Cinema;
import es.udc.paproject.backend.model.entities.CinemaDao;
import es.udc.paproject.backend.model.entities.City;
import es.udc.paproject.backend.model.entities.CityDao;
import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.MovieDao;
import es.udc.paproject.backend.model.entities.Session;
import es.udc.paproject.backend.model.entities.SessionDao;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.InvalidDateException;
import es.udc.paproject.backend.model.exceptions.LateForTheSessionException;
import es.udc.paproject.backend.model.services.dtos.MovieShowtime;

@Service
@Transactional(readOnly = true)
public class ShowtimeServiceImpl implements ShowtimeService {

	@Autowired
	private CityDao cityDao;

	@Autowired
	private CinemaDao cinemaDao;

	@Autowired
	private MovieDao movieDao;

	@Autowired
	private SessionDao sessionDao;

	@Override
	public List<City> findAllCities() {

		Iterable<City> cities = cityDao.findAll(Sort.by(Sort.Direction.ASC, "name"));
		List<City> citiesAsList = new ArrayList<>();
		cities.forEach(c -> citiesAsList.add(c));

		return citiesAsList;
	}

	@Override
	public List<Cinema> findCinemasByCityId(Long cityId) {

		List<Cinema> cinemas = cinemaDao.findByCityIdOrderByName(cityId);

		return cinemas;
	}

	@Override
	public Movie findMovieById(Long movieId) throws InstanceNotFoundException {

		Optional<Movie> movie = movieDao.findById(movieId);

		if (!movie.isPresent()) {
			throw new InstanceNotFoundException("project.entities.movie", movieId);
		}

		return movie.get();
	}

	@Override
	public Session findSessionById(Long sessionId) throws InstanceNotFoundException, LateForTheSessionException {

		Optional<Session> session = sessionDao.findById(sessionId);

		if (!session.isPresent()) {
			throw new InstanceNotFoundException("project.entities.session", sessionId);
		}

		if (!session.get().getDate().isAfter(LocalDateTime.now())) {
			throw new LateForTheSessionException(session.get().getDate());
		}

		return session.get();
	}

	@Override
	public List<MovieShowtime> findMovieShowtimes(Long cinemaId, LocalDate date) throws InvalidDateException {

		List<MovieShowtime> movieShowtimes;
		LocalDateTime startDateTime, endDateTime;
		LocalDate startDate;

		if (date == null || date.equals(LocalDate.now()))
			startDateTime = LocalDateTime.now();
		else
			startDateTime = date.atStartOfDay();

		startDate = startDateTime.toLocalDate();

		endDateTime = startDate.atTime(23, 59, 59);

		if (startDate.isBefore(LocalDate.now())) {
			throw new InvalidDateException(startDate);
		}

		if (startDate.isAfter(LocalDate.now().plusDays(6))) {
			throw new InvalidDateException(startDate);
		}

		List<Session> sessions = sessionDao.findSessionsByDateAndCinemaId(cinemaId, startDateTime, endDateTime);
		movieShowtimes = new ArrayList<MovieShowtime>();

		MovieShowtime movieShowtime = null;
		for (Session session : sessions) {

			if (movieShowtime == null)
				movieShowtime = new MovieShowtime(session.getMovie());

			if (session.getMovie() != movieShowtime.getMovie()) {
				movieShowtimes.add(movieShowtime);
				movieShowtime = new MovieShowtime(session.getMovie());
			}

			movieShowtime.getSessions().add(session);
		}

		if (movieShowtime != null)
			movieShowtimes.add(movieShowtime);

		return movieShowtimes;
	}

}
