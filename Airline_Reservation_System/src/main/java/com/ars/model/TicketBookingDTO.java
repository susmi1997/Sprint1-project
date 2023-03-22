package com.ars.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.ars.entity.Airline;
import com.ars.entity.Flight;
import com.ars.entity.Passenger;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class TicketBookingDTO {

	
	private int ticketId;
	private int no_of_passenger;
	private float total_fare;
	private LocalDate date;
	private Flight fid;
	private Airline aid;	
	private Passenger pid;
}