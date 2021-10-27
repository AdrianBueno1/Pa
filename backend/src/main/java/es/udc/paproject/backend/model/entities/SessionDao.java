package es.udc.paproject.backend.model.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SessionDao extends PagingAndSortingRepository<Session, Long> {

	@Query("SELECT s FROM Session s WHERE s.hall.cinema.id = ?1 AND (s.date BETWEEN ?2 AND ?3) ORDER BY s.movie.title, date")
	public List<Session> findSessionsByDateAndCinemaId(Long cinemaId, LocalDateTime stardate, LocalDateTime endDate);

}
