package es.udc.paproject.backend.rest.dtos;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import es.udc.paproject.backend.model.entities.Session;

public class SessionConversor {

	private SessionConversor() {
	}

	public final static SessionDto toSessionDto(Session session) {
		return new SessionDto(session.getMovie().getTitle(), session.getMovie().getDuration(),
				session.getHall().getName(), toMillis(session.getDate()), session.getPrice(),
				session.getAvailableSeats(), session.getHall().getCinema().getName());
	}

	public final static SessionSummaryDto toSessionSummaryDto(Session session) {
		return new SessionSummaryDto(session.getId(), toMillis(session.getDate()));
	}

	private final static long toMillis(LocalDateTime date) {
		return date.truncatedTo(ChronoUnit.MINUTES).atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
	}
}
