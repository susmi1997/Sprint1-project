package com.ars.model;

import java.time.LocalDate;

import com.ars.entity.Airline;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FlightDTO {

	private int flightId;
	private int availableSeats;
	private int totalSeats;
	private String travellerClass;
	private String time;
	private LocalDate date;
	private String source;
	private String destination;
	private Airline airline;
	
	
}