package com.ars.service;

import java.time.LocalDate;

import com.ars.entity.Airline;
import com.ars.entity.Flight;
import com.ars.entity.Passenger;
import com.ars.entity.TicketBooking;
import com.ars.model.TicketBookingDTO;

public interface TicketService {
	TicketBookingDTO bookFlight(int flight_id,LocalDate date,String pEmail,int no_of_passenger,String airName);
	void cancelBooking(int id);
	TicketBookingDTO getTicket(int id);
	
}