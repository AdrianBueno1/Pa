package es.udc.paproject.backend.rest.dtos;

import java.math.BigDecimal;

public class SessionDto {

	private String movieName;
	private short movieDuration;
	private String hallName;
	private long date;
	private BigDecimal price;
	private short availableSeats;
	private String cinemaName;

	public SessionDto() {
	}

	public SessionDto(String movieName, short movieDuration, String hallName, long date, BigDecimal price,
			short availableSeats, String cinemaName) {

		this.movieName = movieName;
		this.movieDuration = movieDuration;
		this.hallName = hallName;
		this.date = date;
		this.price = price;
		this.availableSeats = availableSeats;
		this.cinemaName = cinemaName;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public short getMovieDuration() {
		return movieDuration;
	}

	public void setMovieDuration(short movieDuration) {
		this.movieDuration = movieDuration;
	}

	public String getHallName() {
		return hallName;
	}

	public void setHallName(String hallName) {
		this.hallName = hallName;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public short getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(short availableSeats) {
		this.availableSeats = availableSeats;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

}
