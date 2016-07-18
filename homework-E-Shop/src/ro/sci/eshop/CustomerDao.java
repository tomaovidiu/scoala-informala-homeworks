package ro.sci.eshop;

public interface CustomerDao {

	/**
	 * Delete a customer from Customer database.
	 * 
	 * @param customerId
	 *            - customer to be deleted
	 * @return - true - if the deleted of the customer was successfully.
	 */
	boolean deleteCustomer(int customerId);

	/**
	 * Get a customer by Id.
	 * 
	 * @param customerId
	 *            - customer to get
	 * @return - true if the operation was successfully
	 */
	boolean getCustomerById(int customerId);

	/**
	 * Get all customers from database.
	 * 
	 * @return nr of customers
	 */
	int getAllCustomers();

	/**
	 * Create a customer in data base
	 * 
	 * @param id
	 *            - id of customer
	 * @param name
	 *            - name of customer
	 * @param email
	 *            - email of customer
	 * @return - true if the operation was successfully
	 */
	boolean createCustomer(int id, String name, String email);
}
