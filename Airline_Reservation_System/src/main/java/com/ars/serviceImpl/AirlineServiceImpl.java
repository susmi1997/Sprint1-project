package com.ars.serviceImpl;

import javax.persistence.PersistenceException;

import org.modelmapper.ModelMapper;

import com.ars.dao.AirlineDao;
import com.ars.daoImpl.AirlineDaoImpl;
import com.ars.entity.Airline;
import com.ars.exception.GlobalException;
import com.ars.model.AirlineDTO;
import com.ars.service.AirlineService;

public class AirlineServiceImpl implements AirlineService {

	AirlineDao airDao=new AirlineDaoImpl();

	@Override
	public void registerAirline(Airline airline) {
		
		airDao.registerAirline(airline);
	}

	@Override
	public void assignAirlineToFlight(int flightId, int airId) {
		
		airDao.assignAirlineToFlight(flightId, airId);
	}

	@Override
	public AirlineDTO getAirlineByName(String name)throws GlobalException {
		
		Airline airline=airDao.getAirlineByName(name);
		if(airline!=null) {
			return new ModelMapper().map(airline, AirlineDTO.class);
		}throw new GlobalException("Airline details not exist");
	}

	@Override
	public AirlineDTO updateAirlineById(int id, Airline airline) {
		Airline a=airDao.updateAirlineById(id, airline);
		return new ModelMapper().map(a, AirlineDTO.class);
	}

	@Override
	public void deleteAirline(int id) throws PersistenceException {
		
		airDao.deleteAirline(id);
	}
	

}