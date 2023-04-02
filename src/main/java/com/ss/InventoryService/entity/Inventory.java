package com.ss.InventoryService.entity;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long busId;
	@Column 
	private String availableSeats;
	@Column 
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date lastUpdatedDate;


}
