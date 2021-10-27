package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Movie;

public class MovieConversor {

	private MovieConversor() {
	}

	public final static MovieDto toMovieDto(Movie movie) {
		return new MovieDto(movie.getTitle(), movie.getSummary(), movie.getDuration());
	}
}
