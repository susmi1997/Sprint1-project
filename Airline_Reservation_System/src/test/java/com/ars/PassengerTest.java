package com.ars;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.PersistenceException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.ars.config.HibernateUtil;
import com.ars.dao.PassengerDao;
import com.ars.entity.Passenger;
import com.ars.model.PassengerDTO;
import com.ars.service.PassengerService;
import com.ars.serviceImpl.PassengerServiceImpl;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PassengerTest {
	static PassengerService passengerServ=new PassengerServiceImpl();
	static PassengerDao pDao;
	public static SessionFactory sessionFactory;
	private Session session;
	

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

	@Test
	@Order(1)
	void testRegisterPassenger()
	{
		Transaction tx=session.beginTransaction();
		Passenger pass=Passenger.builder().name("abcd").email("abcd@gmail.com").phno("7998150748").userName("abcd123").password("1234").role("user").build();
		Integer i=(Integer)session.save(pass);
		tx.commit();
		assertThat(i>0).isTrue();
				
		
	}
	
	@Test
	@Order(2)
	void testGetPassengerById()
	{
		PassengerDTO pDto=passengerServ.getPassengerById(4);
		assertThat(pDto.getName()).isEqualTo("abcd");
	}
	
	@Test
	@Order(4)
	void testUpdatePassengerById()
	{
		Transaction tx=session.beginTransaction();
		Passenger p=new Passenger();
		p.setName("pqrs");
		p.setEmail("abcd@gmail.com");
		PassengerDTO pDto=passengerServ.updatePassenger(4, p);
		tx.commit();
		assertThat(pDto.getName()).isEqualTo("pqrs");
		
	}
	
	@Test
	@Order(4)
	@DisplayName(value="Negetive Test Case")
	void testDeletePassenger()
	{
		passengerServ.deletePassenger(10);
		assertThrows(PersistenceException.class,()->passengerServ.deletePassenger(10));
	}

}