package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOTestFail {

	
	private final ItemDAO DAO = new ItemDAO();
	
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
		Item item = new Item(1l,"Black Ops", 20l);
		assertNull(DAO.create(item));
	}
	
	@Test
	public void testReadAll()
	{
		List<Item> item = new ArrayList<>();
		
		assertEquals(item,DAO.readAll());
	}
	
	@Test
	public void testReadLatest()
	{		
		assertNull(DAO.readLatest());
	}
	
	@Test
	public void testReadItem()
	{		
		assertNull(DAO.readItem(4L));
	}
	
	@Test
	public void testUpdate()
	{		
		Item item = new Item(1L, "Peter", 2L);
		assertNull(DAO.update(item));
	}
	
	@Test
	public void testDelete()
	{		
		assertEquals(0,DAO.delete(1l));
	}
}
