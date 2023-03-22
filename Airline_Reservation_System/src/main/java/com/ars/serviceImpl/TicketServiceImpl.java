package com.ars.serviceImpl;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;

import com.ars.dao.AirlineDao;
import com.ars.dao.FlightDao;
import com.ars.dao.PassengerDao;
import com.ars.dao.TicketDAO;
import com.ars.daoImpl.AirlineDaoImpl;
import com.ars.daoImpl.FlightDaoImpl;
import com.ars.daoImpl.PassengerDaoImpl;
import com.ars.daoImpl.TicketDaoImpl;
import com.ars.entity.Airline;
import com.ars.entity.Flight;
import com.ars.entity.Passenger;
import com.ars.entity.TicketBooking;
import com.ars.exception.GlobalException;
import com.ars.model.TicketBookingDTO;
import com.ars.service.TicketService;

public class TicketServiceImpl implements TicketService{
FlightDao fDao=new FlightDaoImpl();
AirlineDao aDao=new AirlineDaoImpl();
PassengerDao pDao=new PassengerDaoImpl();
TicketDAO tDao=new TicketDaoImpl();
	@Override
	public TicketBookingDTO bookFlight(int flight_id,LocalDate date,String pEmail,int no_of_passenger,String airName) {
		Flight f=fDao.getFlight(flight_id);
		if(f.getAvailalbleSeats()>no_of_passenger)
		{
			Passenger p=pDao.getPassengerByEmail(pEmail);
			Airline airline=aDao.getAirlineByName(airName);
			float totalFare=airline.getAirFare()*no_of_passenger;
			int available_seat=(f.getAvailalbleSeats()-no_of_passenger);
			TicketBooking bookTicket=tDao.bookFlight(airline, p, date, f, no_of_passenger, totalFare, available_seat);
			return new ModelMapper().map(bookTicket, TicketBookingDTO.class);
		}
		return null;
	}

	@Override
	public void cancelBooking(int id) {
		tDao.cancelBooking(id);
		
	}

	@Override
	public TicketBookingDTO getTicket(int id) {
		TicketBooking tick=tDao.getTicket(id);
		if(tick!=null)
			return new ModelMapper().map(tick, TicketBookingDTO.class);
	
	
	throw new GlobalException("Passenger details not exist!!!");
		
	}

}