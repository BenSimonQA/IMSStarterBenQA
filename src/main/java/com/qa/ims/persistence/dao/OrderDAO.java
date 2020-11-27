package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {

	public static final Logger LOGGER = LogManager.getLogger();
	ItemDAO itemDAO = new ItemDAO();
	CustomerDAO customerDAO = new CustomerDAO();

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Long customer_id = resultSet.getLong("fk_customer_id");
        List<Item> items = readLines(resultSet.getLong("id"));
        Long total = 0l;
        for(Item item: items)
        {
        	total += item.getPrice();
        }
        return new Order(id, customer_id, items, total);
    }

	/**
	 * Reads all orders from the database
	 * 
	 * @return A list of orders
	 */
	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from orders");) {
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Order readOrder(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders where id = " + id);) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public List<Order> getCustomerOrder(Long id)
	{
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders where fk_customer_id = " + id);) {
	           List<Order> orders = new ArrayList<Order>();
	           while (resultSet.next()) {
	        	   OrderDAO order = new OrderDAO();
	        	   orders.add(order.readOrder(resultSet.getLong("id")));
	           }
			return orders;
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Creates a orders in the database
	 * 
	 * @param order - takes in a order object. id will be ignored
	 */
	@Override
	public Order create(Order orders) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO orders(fk_customer_id) values('" + orders.getCustomerId() + "')");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Order createItem(long orderID, long itemID)
	{
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO orderslists( fk_order_id, fk_item_id) values('" + orderID 
			+ "','" + itemID + "')");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public Order readCustomer(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders where id = " + id);) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public List<Item> readLines(Long id) {
	     try (Connection connection = DBUtils.getInstance().getConnection();
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery("SELECT * FROM orderslists where fk_order_id = " + id);) {
	           List<Item> items = new ArrayList<Item>();
	           while (resultSet.next()) {
			items.add(itemDAO.readItem(resultSet.getLong("fk_item_id")));
	           }
	           return items;
	        } catch (Exception e) {
	     LOGGER.debug(e);
	     LOGGER.error(e.getMessage());
	     }
	     return null;
	}
	
	/**
	 * Updates a orders in the database
	 * 
	 * @param order - takes in a order object, the id field will be used to
	 *                 update that order in the database
	 * @return
	 */
	@Override
	public Order update(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("update orders set fk_customer_id ='" + order.getCustomerId() + "' where id =" + order.getId());
			return readCustomer(order.getId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Deletes a order in the database
	 * 
	 * @param id - id of the order
	 */
	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			return statement.executeUpdate("delete from orders where id = " + id);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	
	public int deleteCustomer(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			return statement.executeUpdate("delete from orders where fk_customer_id = " + id);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}	
	
	
	public int deleteAllItem(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			return statement.executeUpdate("delete from orderslists where fk_order_id="+ id );
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	
	public int deleteItem(long id, long item_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			return statement.executeUpdate("delete from orderslists where fk_item_id ='" + item_id + "' && fk_order_id='"+ id + "' limit 1");
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	
	public int deleteItemFromAll(long item_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			return statement.executeUpdate("delete from orderslists where fk_item_id =" + item_id);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
}
