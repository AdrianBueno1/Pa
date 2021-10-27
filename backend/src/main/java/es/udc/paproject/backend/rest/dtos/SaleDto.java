package es.udc.paproject.backend.rest.dtos;

import java.math.BigDecimal;

public class SaleDto {

	private long saleDate;
	private String movieTitle;
	private byte seats;
	private BigDecimal totalPrice;
	private long sessionDate;
	private String cinemaName;

	public SaleDto() {
	}

	public SaleDto(long saleDate, String movieTitle, byte seats, BigDecimal totalPrice, long sessionDate,
			String cinemaName) {

		this.saleDate = saleDate;
		this.movieTitle = movieTitle;
		this.seats = seats;
		this.totalPrice = totalPrice;
		this.sessionDate = sessionDate;
		this.cinemaName = cinemaName;
	}

	public long getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(long saleDate) {
		this.saleDate = saleDate;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public byte getSeats() {
		return seats;
	}

	public void setSeats(byte seats) {
		this.seats = seats;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public long getSessionDate() {
		return sessionDate;
	}

	public void setSessionDate(long sessionDate) {
		this.sessionDate = sessionDate;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

}
