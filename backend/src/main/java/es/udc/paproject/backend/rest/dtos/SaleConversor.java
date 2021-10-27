package es.udc.paproject.backend.rest.dtos;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import es.udc.paproject.backend.model.entities.Sale;

public class SaleConversor {

	private SaleConversor() {
	}

	public final static List<SaleDto> toSaleDtos(List<Sale> sales) {
		return sales.stream().map(s -> toSaleDto(s)).collect(Collectors.toList());
	}

	public final static SaleDto toSaleDto(Sale sale) {

		return new SaleDto(toMillis(sale.getDate()), sale.getSession().getMovie().getTitle(), sale.getSeats(),
				sale.getTotalPrice(), toMillis(sale.getSession().getDate()),
				sale.getSession().getHall().getCinema().getName());

	}

	private final static long toMillis(LocalDateTime date) {
		return date.truncatedTo(ChronoUnit.MINUTES).atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
	}

}
