package es.udc.paproject.backend.rest.dtos;

import java.util.List;

public class MovieShowtimeDto {

	private Long id;
	private String movieName;
	private List<SessionSummaryDto> sessions;

	public MovieShowtimeDto() {
	}

	public MovieShowtimeDto(Long id, String movieName, List<SessionSummaryDto> sessions) {
		this.id = id;
		this.movieName = movieName;
		this.sessions = sessions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public List<SessionSummaryDto> getSessions() {
		return sessions;
	}

	public void setSessions(List<SessionSummaryDto> sessions) {
		this.sessions = sessions;
	}

}
