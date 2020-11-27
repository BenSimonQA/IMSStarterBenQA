package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}

	/**
	 * Reads all order to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}

	/**
	 * Creates a order by taking in user input
	 */
	@Override
	public Order create() {
		LOGGER.info("Please enter a customer ID");
		Long customerID = utils.getLong();
		Order order = orderDAO.create(new Order(customerID));
		String answer;
		do {
			LOGGER.info("Please enter a item ID");
			Long itemID = utils.getLong();
			orderDAO.createItem(order.getId(), itemID);
			LOGGER.info("Add more items to order?");
			answer = utils.getString();
		}while(answer.toLowerCase().equals("yes"));
		LOGGER.info("Order created");
		order = orderDAO.readLatest();
		return order;
	}

	/**
	 * Updates an existing order by taking in user input
	 */
	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter a customer ID");
		Long customerID = utils.getLong();
		Order order = orderDAO.update(new Order(id, customerID));
		LOGGER.info("Would you like to delete or add and Item");
		String action= utils.getString();
		if(action.toLowerCase().equals("delete")) {
			LOGGER.info("Please enter a item ID");
			Long itemID = utils.getLong();
			orderDAO.deleteItem(id, itemID);
		}
		else if(action.toLowerCase().equals("add")) {
			String answer;
			do {
				LOGGER.info("Please enter a item ID");
				Long itemID = utils.getLong();
				orderDAO.createItem(order.getId(), itemID);
				LOGGER.info("Add more items to order?");
				answer = utils.getString();
			}while(answer.toLowerCase().equals("yes"));
		}
		else {
			System.out.println("Invalid Input!");
		}
		LOGGER.info("Order Updated");
		return order;
	}

	/**
	 * Deletes an existing order by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = utils.getLong();
		orderDAO.deleteAllItem(id);
		return orderDAO.delete(id);
	}

	
}
