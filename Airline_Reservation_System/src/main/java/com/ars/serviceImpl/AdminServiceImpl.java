package com.ars.serviceImpl;

import javax.persistence.PersistenceException;

import org.modelmapper.ModelMapper;

import com.ars.dao.AdminDao;
import com.ars.daoImpl.AdminDaoImpl;
import com.ars.entity.Admin;
import com.ars.exception.GlobalException;
import com.ars.model.AdminDTO;
import com.ars.service.AdminService;

public class AdminServiceImpl implements AdminService{

	AdminDao aDao=new AdminDaoImpl();
	@Override
	public void registerAdmin(Admin admin) {
		
		aDao.registerAdmin(admin);
	}

	@Override
	public boolean loginAdmin(String userName, String password) {		
		
		return aDao.loginAdmin(userName, password);
	}

	@Override
	public AdminDTO getAdminById(int id) throws GlobalException {
		Admin admin=aDao.getAdmin(id);
		return new ModelMapper().map(admin, AdminDTO.class);
	}

	@Override
	public AdminDTO updateAdmin(int id, Admin admin) throws GlobalException {
		Admin admin2=aDao.updateAdmin(id, admin);
		return new ModelMapper().map(admin2, AdminDTO.class);
	}

	@Override
	public void deleteAdmin(int id) throws PersistenceException {
		aDao.deleteAdmin(id);
		
	}

}