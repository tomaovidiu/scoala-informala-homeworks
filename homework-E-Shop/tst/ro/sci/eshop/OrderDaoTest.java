package ro.sci.eshop;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

public class OrderDaoTest extends AbstractModelDao {

	@BeforeClass
	public static void setUpTests() {
		OrderDao orderDao = new OrderDaoImpl();
		orderDao.getAllOrders();
		for (int i = 11; i < 21; i++) {
			orderDao.createOrder(i, i*123, new Date(), true, i);
		}
		orderDao.getAllOrders();
	}

	@Test
	public void testCreatedorderWithGetorderByIdMethod() {
		// given
		OrderDao orderDao = new OrderDaoImpl();
		boolean booleanCondition = true;
		// when
		for (int i = 12; i < 21; i++) {
			booleanCondition = booleanCondition && orderDao.getOrderById(i);
		}
		// then
		assertTrue(booleanCondition);
	}

	@Test
	public void testCreatedAndDeletedOneorder() {
		// given
		OrderDao orderDao = new OrderDaoImpl();
		int initialNrOforders = orderDao.getAllOrders();
		// when
		orderDao.createOrder(1091, 123, new Date(), false,10);
		orderDao.deleteOrder(1091);
		// then
		assertEquals(initialNrOforders, orderDao.getAllOrders());
	}

	@Test
	public void testDeleteIdorder() {
		// given
		OrderDao orderDao = new OrderDaoImpl();
		int initialNrOforders = orderDao.getAllOrders();
		// when
		orderDao.deleteOrder(11);
		// then
		assertEquals(initialNrOforders - 1, orderDao.getAllOrders());
	}

	@Test
	public void testDeleteMultipleorder() {
		// given
		OrderDao orderDao = new OrderDaoImpl();
		int nrOforders = orderDao.getAllOrders();
		// when
		for (int i = 12; i < 21; i++) {
			orderDao.deleteOrder(i);
		}
		// then
		assertEquals(nrOforders - 9, orderDao.getAllOrders());
	}

}
