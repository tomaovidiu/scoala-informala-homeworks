package ro.sci.eshop;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class CustomerDaoTest extends AbstractModelDao {

	@BeforeClass
	public static void setUpTests() {
		CustomerDao customerDao = new CustomerDaoImpl();
		customerDao.getAllCustomers();
		for (int i = 11; i < 21; i++) {
			customerDao.createCustomer(i, "customer" + i, "cust" + i + "@customer.com");
		}
		customerDao.getAllCustomers();
	}

	@Test
	public void testCreatedCustomerWithGetCustomerByIdMethod() {
		// given
		CustomerDao customerDao = new CustomerDaoImpl();
		boolean booleanCondition = true;
		// when
		for (int i = 12; i < 21; i++) {
			booleanCondition = booleanCondition && customerDao.getCustomerById(i);
		}
		// then
		assertTrue(booleanCondition);
	}

	@Test
	public void testCreatedAndDeletedOneCustomer() {
		// given
		CustomerDao customerDao = new CustomerDaoImpl();
		int initialNrOfCustomers = customerDao.getAllCustomers();
		// when
		customerDao.createCustomer(1091, "Test Name", "test@test.com");
		customerDao.deleteCustomer(1091);
		// then
		assertEquals(initialNrOfCustomers, customerDao.getAllCustomers());
	}

	@Test
	public void testDeleteIdCustomer() {
		// given
		CustomerDao customerDao = new CustomerDaoImpl();
		int initialNrOfCustomers = customerDao.getAllCustomers();
		// when
		customerDao.deleteCustomer(11);
		// then
		assertEquals(initialNrOfCustomers - 1, customerDao.getAllCustomers());
	}

	@Test
	public void testDeleteMultipleCustomer() {
		// given
		CustomerDao customerDao = new CustomerDaoImpl();
		int nrOfCustomers = customerDao.getAllCustomers();
		// when
		for (int i = 12; i < 21; i++) {
			customerDao.deleteCustomer(i);
		}
		// then
		assertEquals(nrOfCustomers - 9, customerDao.getAllCustomers());
	}

}
