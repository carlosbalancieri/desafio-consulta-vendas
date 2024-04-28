package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

public class ReportDTO {

	
	private Long id;
	private Double amount;
	private LocalDate date;
	private String name;
	
	public ReportDTO(Long id, LocalDate date, Double amount, String name) {
		super();
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
		
	
}
