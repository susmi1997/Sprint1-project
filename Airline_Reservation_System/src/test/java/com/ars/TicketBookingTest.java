package com.ars;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ars.config.HibernateUtil;
import com.ars.model.TicketBookingDTO;
import com.ars.service.TicketService;
import com.ars.serviceImpl.TicketServiceImpl;

class TicketBookingTest {
	public static SessionFactory sessionFactory;
	private Session session;
	TicketService tServ=new TicketServiceImpl();

	@BeforeAll
	static void setUp()
	{
		sessionFactory=HibernateUtil.getSessionFactory();
	}
	@BeforeEach
	void openSession()
	{
		session=sessionFactory.openSession();
		
	}
	@AfterEach
	void closeSession()
	{
		if(session!=null)
			session.close();
		System.out.println("session closed");
	}
	
	@AfterAll
	static void tearDown()
	{
		if(sessionFactory!=null)
			sessionFactory.close();
		System.out.println("session factory closed");
	}
	//test for ticket booking
//	@Test
//	void testBookFlight()
//	{
//		Transaction tx=session.beginTransaction();
//		TicketBookingDTO tDto=tServ.bookFlight(1, LocalDate.of(2023, 03, 16), "sri@gmail.com", 3, "Indigo");		
//		tx.commit();
//		assertThat(tDto.getNo_of_passenger()).isEqualTo(3);
//	}
	//test for get ticket
	@Test
	void testGetTicket()
	{
		TicketBookingDTO tD= tServ.getTicket(22769);
		assertThat(tD.getNo_of_passenger()).isEqualTo(0);
	}

}