package ro.sci.eshop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementation of ProductDao interface.
 * 
 * @author Ovidiu
 *
 */
public class ProductDaoImpl extends AbstractModelDao implements ProductDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.eshop.ProductDao#createProduct(int, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean createProduct(int id, String name, String description) {
		Connection conn = getDbConnection();
		if (conn == null)
			return false;
		System.out.println("---Create a product");
		String sqlQuery = "INSERT INTO products(id, name, description) VALUES (?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(sqlQuery);) {
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, description);
			int rs = ps.executeUpdate();
			// System.out.println("rs " + rs);
			if (rs == 1) {
				System.out.println("Product " + name + " added!");
			}
		} catch (SQLException e) {
			System.err.println("Cannot execute query: " + e.getMessage());
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.eshop.ProductDao#deleteProduct(int)
	 */
	@Override
	public boolean deleteProduct(int productId) {
		Connection conn = getDbConnection();
		if (conn == null)
			return false;
		PreparedStatement ps = null;
		int deletedSuccesfull = 0;
		try {
			String sqlQuery = "delete from products where id=?";
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, productId);
			deletedSuccesfull = ps.executeUpdate();
			System.out.println("---Delete a product");
			if (deletedSuccesfull == 1) {
				System.out.println("Product id " + productId + " deleted !");
				return true;
			} else {
				System.out.println("Error: Product NOT deleted");
				return false;
			}
		} catch (SQLException e) {
			System.err.println("Cannot execute query: " + e.getMessage());
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
				}
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.eshop.ProductDao#getProductById(int)
	 */
	@Override
	public boolean getProductById(int productId) {
		Connection conn = getDbConnection();

		if (conn == null)
			return false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		final String format = "%2s%20s%32s\n";
		System.out.println("---Display a product");

		try {
			String sqlQuery = "select * from products where id=?";
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, productId); // first product
			rs = ps.executeQuery();
			boolean hasResults = rs.next();
			if (hasResults) {
				System.out.println("Display Id product: " + productId);
				System.out.format(format, "id", "name", "description");
				do {
					System.out.format(format, rs.getInt("id"), rs.getString("name"), rs.getString("description"));
				} while (rs.next());
				return true;
			} else {
				System.out.println("No results on display id product");
				return false;
			}
		} catch (SQLException e) {
			System.err.println("Cannot execute query: " + e.getMessage());
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
				}
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.eshop.ProductDao#getAllProducts()
	 */
	@Override
	public int getAllProducts() {
		Connection conn = getDbConnection();
		if (conn == null)
			return 0;
		PreparedStatement ps = null;
		ResultSet rs = null;

		final String format = "%2s%20s%32s\n";
		int nrOfProducts = 0;
		System.out.println("---Display all products");
		try {
			String sqlQuery = "select * from products";
			ps = conn.prepareStatement(sqlQuery);
			rs = ps.executeQuery();

			boolean hasResults = rs.next();
			if (hasResults) {
				System.out.format(format, "id", "name", "description");
				do {
					System.out.format(format, rs.getInt("id"), rs.getString("name"), rs.getString("description"));
					nrOfProducts++;
				} while (rs.next());
			} else {
				System.out.println("No results on display all products");
			}
		} catch (SQLException e) {
			System.err.println("Cannot execute query: " + e.getMessage());
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
				}
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
		return nrOfProducts;
	}
}
