package com.ars;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ars.config.HibernateUtil;
import com.ars.dao.AirlineDao;
import com.ars.daoImpl.AirlineDaoImpl;
import com.ars.entity.Flight;
import com.ars.model.FlightDTO;
import com.ars.service.AirlineService;
import com.ars.service.FlightService;
import com.ars.serviceImpl.AirlineServiceImpl;
import com.ars.serviceImpl.FlightServiceImpl;

class FlightTest {
	FlightService fServ=new FlightServiceImpl();
	public static SessionFactory sessionFactory;
	private Session session;
	AirlineService aServ=new AirlineServiceImpl();
	AirlineDao aDao=new AirlineDaoImpl();

	@BeforeAll
	static void setUp()  {
		sessionFactory=HibernateUtil.getSessionFactory();
	}

	@AfterAll
	static void tearDown() {
		if(sessionFactory!=null)
			sessionFactory.close();
		System.out.println("session factory closed");
	}

	@BeforeEach
	void openSession() {
		session=sessionFactory.openSession();
	}

	@AfterEach
	void closeSession()  {
		if(session!=null)
			session.close();
		System.out.println("session closed");
	}
	
	//test for save flight
	@Test
	void testSaveFlight()
	{
		Transaction tx=session.beginTransaction();
		Flight f=Flight.builder().availalbleSeats(20).totalSeats(150).travellerClass("business").source("kolkata").destination("delhi").date(LocalDate.of(2023, 03, 17)).time("10:00 AM").airline(aDao.getAirlineByName("Indigo")).build();
		fServ.saveFlight(f);
		List<Flight> flightL=new ArrayList<Flight>();
		flightL.add(f);
		aDao.getAirlineByName("Indigo").setFlights(flightL);
		tx.commit();
		assertThat(aDao.getAirlineByName("Indigo").getAirId()).isEqualTo(f.getAirline().getAirId());
		
	}
	//test for get flight by Id
	@Test
	void testGetFlightById()
	{
		FlightDTO fDto=fServ.getFlight(1);
		assertThat(fDto.getTotalSeats()).isEqualTo(100);
	}
	//test for update flight
	@Test
	void testUpdateFlight()
	{
		Transaction tx=session.beginTransaction();
		Flight f=new Flight();
		f.setAvailalbleSeats(44);
		FlightDTO fDto=fServ.updateFlight(4, f);
		tx.commit();
		assertThat(fDto.getAvailableSeats()).isEqualTo(44);
	}
	
	

}