package es.udc.paproject.backend.rest.dtos;

import static es.udc.paproject.backend.rest.dtos.SessionConversor.toSessionSummaryDto;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.paproject.backend.model.services.dtos.MovieShowtime;

public class MovieShowtimeConversor {

	private MovieShowtimeConversor() {
	}

	public final static List<MovieShowtimeDto> toMovieShowtimeDtos(List<MovieShowtime> movieShowtimes) {
		return movieShowtimes.stream().map(m -> toMovieShowtimeDto(m)).collect(Collectors.toList());
	}

	public final static MovieShowtimeDto toMovieShowtimeDto(MovieShowtime movieShowtime) {
		List<SessionSummaryDto> sessions = movieShowtime.getSessions().stream().map(s -> toSessionSummaryDto(s))
				.collect(Collectors.toList());

		return new MovieShowtimeDto(movieShowtime.getMovie().getId(), movieShowtime.getMovie().getTitle(), sessions);
	}

}
