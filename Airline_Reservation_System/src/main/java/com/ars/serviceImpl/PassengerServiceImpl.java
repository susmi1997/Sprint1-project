package com.ars.serviceImpl;

import javax.persistence.PersistenceException;

import org.modelmapper.ModelMapper;

import com.ars.dao.PassengerDao;
import com.ars.daoImpl.PassengerDaoImpl;
import com.ars.entity.Passenger;
import com.ars.exception.GlobalException;
import com.ars.model.PassengerDTO;
import com.ars.service.PassengerService;

public class PassengerServiceImpl implements PassengerService{

	PassengerDao pDao=new PassengerDaoImpl();
	@Override
	public void savePassenger(Passenger passenger) {
		
		pDao.savePassenger(passenger);
	}

	@Override
	public boolean login(String userName, String password) {
		
		return pDao.login(userName, password);
	}

	@Override
	public PassengerDTO getPassengerById(int id) throws GlobalException {
		Passenger passenger=pDao.getPassenger(id);
		
		return new ModelMapper().map(passenger, PassengerDTO.class);
	}

	@Override
	public PassengerDTO updatePassenger(int id, Passenger passenger) throws GlobalException {
		Passenger passenger2=pDao.updatePassenger(id, passenger);
		return new ModelMapper().map(passenger2, PassengerDTO.class);
	}

	@Override
	public void deletePassenger(int id) throws PersistenceException {
		pDao.deletePassenger(id);
		
	}

}