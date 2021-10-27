package es.udc.paproject.backend.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.paproject.backend.model.entities.Cinema;

public class CinemaConversor {
	
	private CinemaConversor() {	
	}
	
	public final static List<CinemaDto> toCinemaDtos(List<Cinema> cinemas) {
		return cinemas.stream().map(cinema -> toCinemaDto(cinema)).collect(Collectors.toList());
	}
	private final static CinemaDto toCinemaDto(Cinema cinema) {
		return new CinemaDto(cinema.getId(), cinema.getName());
	}
}
