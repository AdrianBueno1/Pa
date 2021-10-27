package es.udc.paproject.backend.model.services.dtos;

import java.util.ArrayList;
import java.util.List;

import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.Session;

public class MovieShowtime {

	private Movie movie;
	private List<Session> sessions;

	public MovieShowtime() {
	}

	public MovieShowtime(Movie movie) {
		this.movie = movie;
		this.sessions = new ArrayList<Session>();
	}

	public MovieShowtime(Movie movie, List<Session> sessions) {
		this.movie = movie;
		this.sessions = sessions;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((movie == null) ? 0 : movie.hashCode());
		result = prime * result + ((sessions == null) ? 0 : sessions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovieShowtime other = (MovieShowtime) obj;
		if (movie == null) {
			if (other.movie != null)
				return false;
		} else if (!movie.equals(other.movie))
			return false;
		if (sessions == null) {
			if (other.sessions != null)
				return false;
		} else if (!sessions.equals(other.sessions))
			return false;
		return true;
	}

}
