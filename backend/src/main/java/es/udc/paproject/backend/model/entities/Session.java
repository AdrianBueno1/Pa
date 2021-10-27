package es.udc.paproject.backend.model.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
@org.hibernate.annotations.BatchSize(size = 10)
public class Session {

	private Long id;
	private Movie movie;
	private Hall hall;
	private LocalDateTime date;
	private BigDecimal price;
	private short availableSeats;
	private long version;

	Session() {
	}

	public Session(Movie movie, Hall hall, LocalDateTime date, BigDecimal price) {
		this.movie = movie;
		this.hall = hall;
		setDate(date);
		setPrice(price);
		this.availableSeats = hall.getCapacity();
	}

	public Session(Movie movie, Hall hall, LocalDateTime date, BigDecimal price, short availableSeats) {
		this.movie = movie;
		this.hall = hall;
		setDate(date);
		setPrice(price);
		this.availableSeats = availableSeats;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "movieId")
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "hallId")
	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		if (date != null) {
			this.date = date.withNano(0);
		} else {
			this.date = null;
		}
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price.setScale(2, RoundingMode.HALF_EVEN);
	}

	public short getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(short availableSeats) {
		this.availableSeats = availableSeats;
	}

	@Version
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

}
