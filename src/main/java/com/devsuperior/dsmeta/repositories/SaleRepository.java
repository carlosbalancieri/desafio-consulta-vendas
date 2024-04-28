package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	@Query("SELECT obj FROM Sale obj " +
		   "WHERE obj.date BETWEEN :minDate AND :maxDate")
	Page<Sale> searchTeste(LocalDate minDate, LocalDate maxDate, Pageable pageable);
	
	@Query("SELECT new com.devsuperior.dsmeta.dto.SummaryDTO( sl.name, SUM(s.amount) ) FROM Sale s " +
		   "JOIN s.seller sl " +
		   "WHERE s.date BETWEEN :minDate AND :maxDate " +
		   "GROUP BY sl.name")
		Page<SummaryDTO> getSummary(LocalDate minDate, LocalDate maxDate, Pageable pageable);
}
