package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.DBUtils;

public class CustomerDAOTestFail {

	private final CustomerDAO DAO = new CustomerDAO();
	
	@BeforeClass
	public static void init() {
		DBUtils.connect("root", "ben..QAIMS");
	}

	@Before
	public void setup() {
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate()
	{
		assertNull(DAO.create(new Customer(3l,"Fred","Prank")));
	}
	
	@Test
	public void testReadAll()
	{
		List<Customer> customer = new ArrayList<>();
		
		assertEquals(customer,DAO.readAll());
	}
	
	@Test
	public void testReadLatest()
	{		
		assertNull(DAO.readLatest());
	}
	
	@Test
	public void testReadCustomer()
	{		
		assertNull(DAO.readCustomer(4L));
	}
	
	@Test
	public void testUpdate()
	{		
		Customer customer = new Customer(1L, "Peter", "Pan");
		assertNull(DAO.update(customer));
	}
	
	@Test
	public void testDelete()
	{		
		assertEquals(0,DAO.delete(1l));
	}
}
