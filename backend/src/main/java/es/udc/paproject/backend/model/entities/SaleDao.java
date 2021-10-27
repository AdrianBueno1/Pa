package es.udc.paproject.backend.model.entities;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SaleDao extends PagingAndSortingRepository<Sale, Long> {

	Slice<Sale> findByUserIdOrderByDateDesc(Long userId, Pageable pageable);
}
