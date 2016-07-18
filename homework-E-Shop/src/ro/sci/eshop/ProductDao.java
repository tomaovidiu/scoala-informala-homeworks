package ro.sci.eshop;

public interface ProductDao {

	/**
	 * Delete a product from product database.
	 * 
	 * @param productId
	 *            - product to be deleted
	 * @return - true - if the deleted of the product was successfully.
	 */
	boolean deleteProduct(int productId);

	/**
	 * Get a product by Id.
	 * 
	 * @param productId
	 *            - product to get
	 * @return - true if the operation was successfully
	 */
	boolean getProductById(int productId);

	/**
	 * Get all products from database.
	 * 
	 * @return nr of products
	 */
	int getAllProducts();

	/**
	 * Create a product in database
	 * 
	 * @param id
	 *            - id of product
	 * @param name
	 *            - name of product
	 * @param string
	 *            - description of product
	 * @return - true if the operation was successfully
	 */
	boolean createProduct(int id, String name, String string);
}
