package com.ars.dao;

import java.time.LocalDate;

import com.ars.entity.Airline;
import com.ars.entity.Flight;
import com.ars.entity.Passenger;
import com.ars.entity.TicketBooking;

public interface TicketDao {
	TicketBooking bookFlight(Airline airline, Passenger p, LocalDate date,Flight flight, int no_of_passenger, float total_fare, int availableSeat);
	void cancelBooking(int id);
	TicketBooking getTicket(int id);
	
		
	
}