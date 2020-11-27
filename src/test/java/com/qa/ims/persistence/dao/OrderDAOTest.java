package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {

	private final OrderDAO DAO = new OrderDAO();

	@BeforeClass
	public static void init() {
		DBUtils.connect("root", "ben.simon.QAIMS");
	}

	@Before
	public void setup() {
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final List <Item> item = new ArrayList<>();
		final Order created = new Order(2l, 1l, item, 0L);
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Order> expected = new ArrayList<>();
		final List <Item> item = new ArrayList<>();
		Item items = new Item(1L, "Black Ops", 20L);
		item.add(items);
		expected.add(new Order(1L, 1L, item, 20L));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadOrder()
	{
		final long ID = 1L;
		final List <Item> item = new ArrayList<>();
		Item items = new Item(1L, "Black Ops", 20L);
		item.add(items);
		assertEquals(new Order(1L, 1L, item, 20L), DAO.readOrder(ID));
	}

	@Test
	public void testGetCustomerOrder()
	{
		List<Order> expected = new ArrayList<>();
		final long ID = 1L;
		final List <Item> item = new ArrayList<>();
		Item items = new Item(1L, "Black Ops", 20L);
		item.add(items);
		expected.add(new Order(1L, 1L, item, 20L));
		assertEquals(expected, DAO.getCustomerOrder(ID));
	}
	
	@Test
	public void testCreateItem()
	{
		final long ID = 1L;
		final List <Item> item = new ArrayList<>();
		Item items = new Item(1L, "Black Ops", 20L);
		item.add(items);
		item.add(items);
		assertEquals(new Order(1L, 1L, item, 20L), DAO.createItem(ID,1L));
	}
	
	@Test
	public void testReadCustomer()
	{
		final long ID = 1L;
		final List <Item> item = new ArrayList<>();
		Item items = new Item(1L, "Black Ops", 20L);
		item.add(items);
		assertEquals(new Order(1L, 1L, item, 20L), DAO.readCustomer(ID));
	}
	
	@Test
	public void testUpdate()
	{
		final List <Item> item = new ArrayList<>();
		Item items = new Item(1L, "Black Ops", 20L);
		item.add(items);
		Order expected = new Order(1L, 2L, item, 20L);
		assertEquals(expected, DAO.update(expected));
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

		assertEquals(1, DAO.deleteAllItem(1L));
	}

	@Test
	public void testDeleteItem()
	{

		assertEquals(1, DAO.deleteItem(1L,1L));
	}
	
	@Test
	public void testDeleteItemFromAll()
	{
		assertEquals(1, DAO.deleteItemFromAll(1L));
	}
}
