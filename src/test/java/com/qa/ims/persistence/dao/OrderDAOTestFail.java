package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTestFail {

	private final OrderDAO DAO = new OrderDAO();

	@BeforeClass
	public static void init() {
		DBUtils.connect("root", "ben..QAIMS");
	}

	@Before
	public void setup() {
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		Order order = new Order(1l,1l);
		assertNull(DAO.create(order));

	}

	@Test
	public void testReadAll() {
		final List <Order> item = new ArrayList<>();
		assertEquals(item, DAO.readAll());
	}
	
	@Test
	public void testReadLatest() {
		final List<Order> order = new ArrayList<>();
		assertEquals(order, DAO.readAll());
	}

	@Test
	public void testReadOrder()
	{
		assertNull(DAO.readOrder(1L));

	}

	@Test
	public void testGetCustomerOrder()
	{
		assertNull(DAO.getCustomerOrder(1L));

	}
	
	@Test
	public void testCreateItem()
	{
		assertNull(DAO.createItem(1L,1L));

	}
	
	@Test
	public void testReadCustomer()
	{
		assertNull(DAO.readCustomer(1L));
	}
	
	@Test
	public void testReadLines()
	{
		assertNull(DAO.readLines(1L));
	}
	
	@Test
	public void testUpdate()
	{
		Order order = new Order(1l,1l);
		assertNull(DAO.update(order));
	}
	
	@Test
	public void testDelete()
	{
		assertEquals(0, DAO.delete(1L));
	}
	
	@Test
	public void testDeleteCustomer()
	{
		assertEquals(0, DAO.deleteCustomer(1L));
	}
	
	@Test
	public void testDeleteAllItem()
	{
		assertEquals(0, DAO.deleteAllItem(0));
	}

	@Test
	public void testDeleteItem()
	{

		assertEquals(0, DAO.deleteItem(1L,1L));
	}
	
	@Test
	public void testDeleteItemFromAll()
	{
		assertEquals(0, DAO.deleteItemFromAll(1L));
	}
	
}
