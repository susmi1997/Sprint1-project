package com.ars.service;

import javax.persistence.PersistenceException;

import com.ars.entity.Admin;
import com.ars.exception.GlobalException;
import com.ars.model.AdminDTO;

public interface AdminService {
	
	void registerAdmin(Admin admin);
	boolean loginAdmin(String userName, String password);
	AdminDTO getAdminById(int id)throws GlobalException;
	AdminDTO updateAdmin(int id, Admin admin)throws GlobalException;
	void deleteAdmin(int id)throws PersistenceException;	

}
