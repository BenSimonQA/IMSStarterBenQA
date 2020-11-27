package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.any;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private OrderDAO dao;

	@InjectMocks
	private OrderController controller;

	@Test
	public void testCreate() {
		final long CUSTOMER_ID = 1L;
		final Order created = new Order(1l, CUSTOMER_ID);
		final String secondAnswer = "no";
		final long ITEM_ID = 1L;


		Mockito.when(utils.getLong()).thenReturn(CUSTOMER_ID, 1L,ITEM_ID);
		Mockito.when(utils.getString()).thenReturn(secondAnswer);
		Mockito.when(dao.create(any(Order.class))).thenReturn(created);
		Mockito.when(dao.readLatest()).thenReturn(created);
		
		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(dao, Mockito.times(1)).create(any(Order.class));
	}

	@Test
	public void testReadAll() {
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, 1L));

		Mockito.when(dao.readAll()).thenReturn(orders);

		assertEquals(orders, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void testUpdate() {
		Order updated = new Order(1L, 2L);

		Mockito.when(this.utils.getLong()).thenReturn(1L, updated.getCustomerId());
		Mockito.when(this.utils.getString()).thenReturn("yes", "no");
		Mockito.when(this.dao.update(updated)).thenReturn(updated);

		assertEquals(updated, this.controller.update());

		Mockito.verify(this.utils, Mockito.times(2)).getLong();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
	}

	@Test
	public void testDelete() {
		final long ID = 2L;

		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(dao.delete(ID)).thenReturn(2);

		assertEquals(2L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(ID);
	}
	
}
