package ro.sci.eshop;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class OrderItemDaoTest extends AbstractModelDao {

	@BeforeClass
	public static void setUpTests() {
		OrderItemDao orderItemDao = new OrderItemDaoImpl();
		orderItemDao.getAllOrderItems();
		for (int i = 11; i < 21; i++) {
			orderItemDao.createOrderItem(i, i + 1, 2, i + 3);
		}
		orderItemDao.getAllOrderItems();
	}

	@Before
	public void testCreatedOrderItemWithGetOrderItemByIdMethod() {
		// given
		OrderItemDao orderItemDao = new OrderItemDaoImpl();
		boolean booleanCondition = true;
		// when
		for (int i = 12; i < 21; i++) {
			booleanCondition = booleanCondition && orderItemDao.getOrderItemById(i);
		}
		// then
		assertTrue(booleanCondition);
	}

	@Test
	public void testCreatedAndDeletedOneOrderItem() {
		// given
		OrderItemDao orderItemDao = new OrderItemDaoImpl();
		int initialNrOfOrderItems = orderItemDao.getAllOrderItems();
		// when
		orderItemDao.createOrderItem(1091, 1, 2, 3);
		orderItemDao.deleteOrderItem(1091);
		// then
		assertEquals(initialNrOfOrderItems, orderItemDao.getAllOrderItems());
	}

	@Test
	public void testDeleteIdOrderItem() {
		// given
		OrderItemDao orderItemDao = new OrderItemDaoImpl();
		int initialNrOfOrderItems = orderItemDao.getAllOrderItems();
		// when
		orderItemDao.deleteOrderItem(11);
		// then
		assertEquals(initialNrOfOrderItems - 1, orderItemDao.getAllOrderItems());
	}

	@Test
	public void testDeleteMultipleOrderItem() {
		// given
		OrderItemDao orderItemDao = new OrderItemDaoImpl();
		int nrOfOrderItems = orderItemDao.getAllOrderItems();
		// when
		for (int i = 12; i < 21; i++) {
			orderItemDao.deleteOrderItem(i);
		}
		// then
		assertEquals(nrOfOrderItems - 9, orderItemDao.getAllOrderItems());
	}

}
