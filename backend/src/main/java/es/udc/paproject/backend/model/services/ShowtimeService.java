package es.udc.paproject.backend.model.services;

import java.time.LocalDate;
import java.util.List;

import es.udc.paproject.backend.model.entities.Cinema;
import es.udc.paproject.backend.model.entities.City;
import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.Session;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.InvalidDateException;
import es.udc.paproject.backend.model.exceptions.LateForTheSessionException;
import es.udc.paproject.backend.model.services.dtos.MovieShowtime;

public interface ShowtimeService {

	public List<City> findAllCities();

	public List<Cinema> findCinemasByCityId(Long cityId);

	public List<MovieShowtime> findMovieShowtimes(Long cinemaId, LocalDate date) throws InvalidDateException;

	public Movie findMovieById(Long movieId) throws InstanceNotFoundException;

	public Session findSessionById(Long sessionId) throws InstanceNotFoundException, LateForTheSessionException;

}
