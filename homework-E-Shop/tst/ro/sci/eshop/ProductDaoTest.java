package ro.sci.eshop;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProductDaoTest extends AbstractModelDao {

	@BeforeClass
	public static void setUpTests() {
		ProductDao productDao = new ProductDaoImpl();
		productDao.getAllProducts();
		for (int i = 11; i < 21; i++) {
			productDao.createProduct(i, "product " + i, " makes every thing you need " + i + " times!");
		}
		productDao.getAllProducts();
	}

	@Test
	public void testCreatedProductWithGetProductByIdMethod() {
		// given
		ProductDao productDao = new ProductDaoImpl();
		boolean booleanCondition = true;
		// when
		for (int i = 12; i < 21; i++) {
			booleanCondition = booleanCondition && productDao.getProductById(i);
		}
		// then
		assertTrue(booleanCondition);
	}

	@Test
	public void testCreatedAndDeletedOneProduct() {
		// given
		ProductDao productDao = new ProductDaoImpl();
		int initialNrOfProducts = productDao.getAllProducts();
		// when
		productDao.createProduct(1091, "Test Name", "test@test.com");
		productDao.deleteProduct(1091);
		// then
		assertEquals(initialNrOfProducts, productDao.getAllProducts());
	}

	@Test
	public void testDeleteIdProduct() {
		// given
		ProductDao productDao = new ProductDaoImpl();
		int initialNrOfProducts = productDao.getAllProducts();
		// when
		productDao.deleteProduct(11);
		// then
		assertEquals(initialNrOfProducts - 1, productDao.getAllProducts());
	}

	@Test
	public void testDeleteMultipleProduct() {
		// given
		ProductDao productDao = new ProductDaoImpl();
		int nrOfProducts = productDao.getAllProducts();
		// when
		for (int i = 12; i < 21; i++) {
			productDao.deleteProduct(i);
		}
		// then
		assertEquals(nrOfProducts - 9, productDao.getAllProducts());
	}

}
