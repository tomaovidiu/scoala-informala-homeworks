package ro.sci.eshop;

import java.util.Date;

public interface OrderDao {

	/**
	 * Delete a order from order database.
	 * 
	 * @param orderId
	 *            - order to be deleted
	 * @return - true - if the deleted of the order was successfully.
	 */
	boolean deleteOrder(int orderId);

	/**
	 * Get a order by Id.
	 * 
	 * @param orderId
	 *            - order to get
	 * @return - true if the operation was successfully
	 */
	boolean getOrderById(int orderId);

	/**
	 * Get all orders from database.
	 * 
	 * @return nr of orders
	 */
	int getAllOrders();

	/**
	 * Create a order in database
	 * 
	 * @param id
	 *            - id of order
	 * @param name
	 *            - name of order
	 * @param email
	 *            - email of order
	 * @return - true if the operation was successfully
	 */
		boolean createOrder(int id, long value, Date date, boolean confirmed, int id_customer);
}
