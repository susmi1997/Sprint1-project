package com.ars.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int flightId;
	private int availalbleSeats;
	private int totalSeats;
	private String travellerClass;
	private String time;
	private LocalDate date;
	private String source;
	private String destination;
	@ManyToOne
	@JoinColumn(name="airId")
	private Airline airline;
}