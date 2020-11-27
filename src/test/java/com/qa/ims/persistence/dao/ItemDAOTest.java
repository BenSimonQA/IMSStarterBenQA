package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOTest {

	private final ItemDAO DAO = new ItemDAO();

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
		final Item created = new Item(3L, "Spider-Man", 50l);
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(1L, "Black Ops", 20l));
		expected.add(new Item(2L, "Tetris", 10l));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(new Item(2L, "Tetris", 10l), DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(new Item(ID, "Black Ops", 20l), DAO.readItem(ID));
	}

	@Test
	public void testUpdate() {
		final Item updated = new Item(1L, "Super Mario", 35l);
		assertEquals(updated, DAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(2));
	}
	
}
