package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	
	public Page<SummaryDTO> getSummary(String minDate, String maxDate, Pageable pageable) {			
		
		LocalDate min;
		LocalDate max;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		if (minDate == null) {
			LocalDate data = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			min = data.minusYears(1L);
		} else {
			min = LocalDate.parse(minDate, formatter);
		}
		
		
		if (maxDate == null) {
			LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			max = today;
		} else {
			max = LocalDate.parse(maxDate, formatter);
		}
		
		Page<SummaryDTO> result = repository.getSummary(min, max, pageable);
		return result;
	}
	
	public Page<ReportDTO> getReport(String minDate, String maxDate, String name, Pageable pageable) {			
		
		LocalDate min;
		LocalDate max;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		if (minDate == null) {
			LocalDate data = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			min = data.minusYears(1L);
		} else {
			min = LocalDate.parse(minDate, formatter);
		}
				
		if (maxDate == null) {
			LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			max = today;
		} else {
			max = LocalDate.parse(maxDate, formatter);
		}
		
		Page<ReportDTO> result = repository.getReport(min, max, name, pageable);
		return result;
	}

}
