package ro.sci.eshop;

public interface OrderItemDao {

	/**
	 * Delete a orderItem from OrderItem database.
	 * 
	 * @param orderItemId
	 *            - orderItem to be deleted
	 * @return - true - if the deleted of the orderItem was successfully.
	 */
	boolean deleteOrderItem(int orderItemId);

	/**
	 * Get a orderItem by Id.
	 * 
	 * @param orderItemId
	 *            - orderItem to get
	 * @return - true if the operation was successfully
	 */
	boolean getOrderItemById(int orderItemId);

	/**
	 * Get all orderItems from database.
	 * 
	 * @return nr of orderItems
	 */
	int getAllOrderItems();

	/**
	 * Create a orderItem in database
	 * 
	 * @param id
	 *            - id of orderItem
	 * @param id_order
	 *            - id_order
	 * @param quantity
	 *            - quantity
	 * @param id_product
	 *            - id_product
	 * @return - true if the operation was successfully
	 */
	boolean createOrderItem(int id, int id_order, int quantity, int id_product);
}