package com.ars.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.PersistenceException;

import org.modelmapper.ModelMapper;

import com.ars.dao.FlightDao;
import com.ars.daoImpl.FlightDaoImpl;
import com.ars.entity.Flight;
import com.ars.exception.GlobalException;
import com.ars.model.FlightDTO;
import com.ars.service.FlightService;

public class FlightServiceImpl implements FlightService{
	FlightDao flightDao=new FlightDaoImpl();
	
	//method to save new flight in database
	@Override
	public void saveFlight(Flight flight) {
		
		flightDao.saveFlight(flight);
	}
	//method to update existing flight details
	@Override
	public FlightDTO updateFlight(int id, Flight flight) {
		Flight f=flightDao.updateFlight(id, flight);
		return new ModelMapper().map(f, FlightDTO.class);
	}
	//method to get flight by Id
	@Override
	public FlightDTO getFlight(int id)throws GlobalException {
		Flight f2=flightDao.getFlight(id);
		if(f2!=null) {
			return new ModelMapper().map(f2, FlightDTO.class);
		}throw new GlobalException("flight details not exist");
	}
	// method to delete flight
	@Override
	public void deleteFlight(int id) throws PersistenceException {
		flightDao.deleteFlight(id);
		
	}
	// method to check available flights form one destination to another
	@Override
	public List<Flight> checkFlight(String from, String to, LocalDate date) {
		
		return flightDao.checkFlight(from, to, date);
	}

}