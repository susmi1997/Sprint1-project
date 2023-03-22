package com.ars.model;


import java.util.List;

import com.ars.entity.Flight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class AirlineDTO {
	
	private int airId;
	private String airName;
	private float airFare;
	private List<Flight> flights;
	
	

}