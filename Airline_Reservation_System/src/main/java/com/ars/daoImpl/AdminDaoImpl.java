package com.ars.daoImpl;

import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.ars.config.HibernateUtil;
import com.ars.dao.AdminDao;
import com.ars.entity.Admin;
import com.ars.entity.Passenger;

public class AdminDaoImpl implements AdminDao {

	@Override
	public void registerAdmin(Admin admin) {
		
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.save(admin);
		session.getTransaction().commit();
		
		session.close();
	}

	@Override
	public boolean loginAdmin(String userName, String password) {
		
		Session session =HibernateUtil.getSession();
		Admin admin=session.get(Admin.class, Integer.parseInt(JOptionPane.showInputDialog("enter id")));
		if(admin.getUserName().equals(userName)&& admin.getPassword().equals(password))
			return true;
		else
			return false;
	}

	@Override
	public Admin getAdmin(int id) {
		try(Session sesssion=HibernateUtil.getSession())
		{
			Admin admin=sesssion.get(Admin.class, id);
			return admin;
		}catch (HibernateException e) {
		System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public Admin updateAdmin(int id, Admin admin) {
		try(Session session=HibernateUtil.getSession())
		{
			Admin adm=(Admin)session.load(Admin.class, id);
			adm.setAName(admin.getAName());
			adm.setEmail(admin.getEmail());
			adm.setUserName(admin.getUserName());
			adm.setPassword(admin.getPassword());
			
			session.beginTransaction();
			session.saveOrUpdate(adm);
			session.getTransaction().commit();
			return adm;
						
		}catch (HibernateException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}

	@Override
	public void deleteAdmin(int id) throws PersistenceException {
	
		try(Session session=HibernateUtil.getSession()){
			Admin admn=(Admin)session.load(Admin.class, id);
			session.beginTransaction();
			session.delete(admn);
			session.getTransaction().commit();
			
		}catch (HibernateException e) {
			System.out.println(e.getMessage());
		}
	}

}