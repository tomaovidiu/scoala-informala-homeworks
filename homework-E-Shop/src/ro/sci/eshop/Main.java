package ro.sci.eshop;

import java.util.Date;

/**
 * Considerand baza de date creata la curs, vrem sa o modificam in felul
 * urmator: adaugam o coloana "id_customer" de tip int in tabela "orders" -
 * adaugam o tabela noua "orderitems" care sa aiba urmatoarele coloane: id
 * (int), id_order (int), quantity (int), id_product (int) - adaugam o tabela
 * noua "products" care sa aiba urmatoarele coloane: id (int), name (varchar
 * 256), description (varchar 1024)
 * 
 * Folosindu-va de exemplul de la curs, scrieti o aplicatie care sa permita: -
 * introducere de date in fiecare tabela - citire de date din fiecare tabela -
 * stergere de date din fiecare tabela nr row creste cu 1,
 * 
 * Logica de mai sus va fi implementata in urmatoarele clase: -
 * ConnectionManager - clasa in care vom avea metoda getConnection(String type,
 * String host, int port, String dbName, String user, String pw) care va returna
 * un java.sql.Connection si care va fi folosita in clasele de mai jos (asta o
 * avem de la curs) - CustomerDaoImpl - clasa care ofera operatii CRUD pentru
 * customer - OrderDaoImpl - clasa care ofera operatii CRUD pentru orders -
 * OrderItemDaoImpl - clasa care ofera operatii CRUD pentru orderitems -
 * ProductDaoImpl - clasa care ofera operatii CRUD pentru products
 * 
 * @author Ovidiu
 *
 */
public class Main {

	public static void main(String[] args) {

		CustomerDao customerDao = new CustomerDaoImpl();
		customerDao.createCustomer(9, "nana", "nana@nana.com");
		customerDao.getCustomerById(9);
		customerDao.getAllCustomers();
		customerDao.deleteCustomer(9);
		customerDao.getAllCustomers();

		OrderDao orderDao = new OrderDaoImpl();
		orderDao.createOrder(1, 999, new Date(), true, 9);
		orderDao.getOrderById(1);
		orderDao.getAllOrders();
		orderDao.deleteOrder(1);
		orderDao.getAllOrders();

		OrderItemDao orderItemDao = new OrderItemDaoImpl();
		orderItemDao.createOrderItem(1, 200, 2, 3);
		orderItemDao.getOrderItemById(1);
		orderItemDao.getAllOrderItems();
		orderItemDao.deleteOrderItem(1);
		orderItemDao.getAllOrderItems();

		ProductDao productDao = new ProductDaoImpl();
		productDao.createProduct(9, "produs1", "cel mai bun!");
		productDao.getProductById(9);
		productDao.getAllProducts();
		productDao.deleteProduct(9);
		productDao.getAllProducts();

	}
}