package com.ars;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ars.config.HibernateUtil;
import com.ars.dao.AirlineDao;
import com.ars.daoImpl.AirlineDaoImpl;
import com.ars.entity.Airline;
import com.ars.entity.Flight;
import com.ars.service.AirlineService;
import com.ars.service.FlightService;
import com.ars.serviceImpl.AirlineServiceImpl;
import com.ars.serviceImpl.FlightServiceImpl;

class AirlineTest {
	FlightService flightService=new FlightServiceImpl();
	AirlineService airlineService=new AirlineServiceImpl();
	AirlineDao airDao=new AirlineDaoImpl();
	public static SessionFactory sessionFactory;
	private Session session;

	@BeforeAll
	static void setUp(){
		sessionFactory=HibernateUtil.getSessionFactory();
	}

	@AfterAll
	static void tearDown()
		{
		if(sessionFactory!=null)
			sessionFactory.close();
		System.out.println("Session factory closed");
		}
	

	@BeforeEach
	void openSession() throws Exception {
		session= sessionFactory.openSession();
	}

	@AfterEach
	void closeSession() throws Exception {
		if(session!=null)
			session.close();
		System.out.println("session closed");
	}

	@Test
	void oneTomanyRelationshipTest()
	{
		Airline airline=Airline.builder().airName("Air India").airFare(2000).build();
		
		Flight flight1=Flight.builder().airline(airline).availalbleSeats(10).destination("delhi").source("pune").time("05:30").date(LocalDate.of(2023, 03,20)).build();
		Flight flight2=Flight.builder().airline(airline).availalbleSeats(10).destination("bangalore").source("mumbai").time("05:30").date(LocalDate.of(2023, 03,22)).build();
		List<Flight> flights=new ArrayList<Flight>();
		flights.add(flight1);
		flights.add(flight2);
		airline.setFlights(flights);
		flightService.saveFlight(flight1);
		flightService.saveFlight(flight2);
		assertThat(flight1.getAirline()).isEqualTo(airline);
		assertThat(flight2.getAirline()).isEqualTo(airline);
		
		assertThat(airline.getFlights().get(0).getFlightId()).isEqualTo(flight1.getFlightId());
		
		assertThat(airline.getFlights().get(1).getFlightId()).isEqualTo(flight2.getFlightId());


		
		}
		

}