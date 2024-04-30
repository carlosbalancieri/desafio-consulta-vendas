package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {		
	
	@Query("SELECT new com.devsuperior.dsmeta.dto.SummaryDTO( sl.name, SUM(s.amount) ) FROM Sale s " +
		   "JOIN s.seller sl " +
		   "WHERE s.date BETWEEN :minDate AND :maxDate " +
		   "GROUP BY sl.name")
		Page<SummaryDTO> getSummary(LocalDate minDate, LocalDate maxDate, Pageable pageable);
	
	
	@Query("SELECT new com.devsuperior.dsmeta.dto.ReportDTO( s.id, s.date, s.amount, sl.name ) " +
	           "FROM Sale s " +
			   "JOIN s.seller sl " +
			   "WHERE s.date BETWEEN :minDate AND :maxDate " +
			"AND (:name IS NULL OR UPPER(sl.name) LIKE UPPER(CONCAT('%', :name, '%')))") 
		Page<ReportDTO> getReport(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);	
}
