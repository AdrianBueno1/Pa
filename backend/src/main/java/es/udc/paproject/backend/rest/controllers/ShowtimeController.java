package es.udc.paproject.backend.rest.controllers;

import static es.udc.paproject.backend.rest.dtos.CinemaConversor.toCinemaDtos;
import static es.udc.paproject.backend.rest.dtos.CityConversor.toCityDtos;
import static es.udc.paproject.backend.rest.dtos.MovieConversor.toMovieDto;
import static es.udc.paproject.backend.rest.dtos.MovieShowtimeConversor.toMovieShowtimeDtos;
import static es.udc.paproject.backend.rest.dtos.SessionConversor.toSessionDto;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.InvalidDateException;
import es.udc.paproject.backend.model.exceptions.LateForTheSessionException;
import es.udc.paproject.backend.model.services.ShowtimeService;
import es.udc.paproject.backend.rest.dtos.CinemaDto;
import es.udc.paproject.backend.rest.dtos.CityDto;
import es.udc.paproject.backend.rest.dtos.MovieDto;
import es.udc.paproject.backend.rest.dtos.MovieShowtimeDto;
import es.udc.paproject.backend.rest.dtos.SessionDto;

@RestController
@RequestMapping("/showtimes")
public class ShowtimeController {

	@Autowired
	private ShowtimeService showtimeService;

	@GetMapping("/cities")
	public List<CityDto> findAllCities() {
		return toCityDtos(showtimeService.findAllCities());
	}

	@GetMapping("/cinemas")
	public List<CinemaDto> findCinemasByCityId(@RequestParam Long cityId) {
		return toCinemaDtos(showtimeService.findCinemasByCityId(cityId));
	}

	@GetMapping("/movies/{id}")
	public MovieDto findMovieById(@PathVariable Long id) throws InstanceNotFoundException {
		return toMovieDto(showtimeService.findMovieById(id));
	}

	@GetMapping("/sessions/{id}")
	public SessionDto findSessionById(@PathVariable Long id)
			throws InstanceNotFoundException, LateForTheSessionException {
		return toSessionDto(showtimeService.findSessionById(id));
	}

	@GetMapping("/cinemas/{id}/movieShowtimes")
	public List<MovieShowtimeDto> findMovieShowtimes(@PathVariable Long id, @RequestParam Long date)
			throws InvalidDateException {
		return toMovieShowtimeDtos(showtimeService.findMovieShowtimes(id, toLocalDate(date)));
	}

	private LocalDate toLocalDate(Long date) {
		return date == null ? null : Instant.ofEpochMilli(date).atZone(ZoneOffset.systemDefault()).toLocalDate();
	}

}
