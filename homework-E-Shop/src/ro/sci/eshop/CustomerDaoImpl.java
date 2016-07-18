package ro.sci.eshop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementation of CustomerDao interface.
 * 
 * @author Ovidiu
 *
 */
public class CustomerDaoImpl extends AbstractModelDao implements CustomerDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.eshop.CustomerDao#createCustomer(int, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean createCustomer(int id, String name, String email) {
		Connection conn = getDbConnection();
		if (conn == null)
			return false;
		System.out.println("---Create a customer");
		String sqlQuery = " INSERT INTO public.customer(id, name, email) VALUES (?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(sqlQuery);) {
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, email);
			int rs = ps.executeUpdate();
			// System.out.println("rs " + rs);
			if (rs == 1) {
				System.out.println("Customer " + name + " added!");
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
	 * @see ro.sci.eshop.CustomerDao#deleteCustomer(int)
	 */
	@Override
	public boolean deleteCustomer(int customerId) {
		Connection conn = getDbConnection();
		if (conn == null)
			return false;
		PreparedStatement ps = null;
		int deletedSuccesfull = 0;
		try {
			String sqlQuery = "delete from customer where id=?";
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, customerId);
			deletedSuccesfull = ps.executeUpdate();
			System.out.println("---Delete a customer");
			if (deletedSuccesfull == 1) {
				System.out.println("Customer id " + customerId + " deleted !");
				return true;
			} else {
				System.out.println("Error: Customer NOT deleted");
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
	 * @see ro.sci.eshop.CustomerDao#getCustomerById(int)
	 */
	@Override
	public boolean getCustomerById(int customerId) {
		Connection conn = getDbConnection();

		if (conn == null)
			return false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		final String format = "%2s%20s%32s\n";
		System.out.println("---Display a customer");

		try {
			String sqlQuery = "select * from customer where id=?";
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, customerId); // first customer
			rs = ps.executeQuery();
			boolean hasResults = rs.next();
			if (hasResults) {
				System.out.println("Display Id customer: " + customerId);
				System.out.format(format, "id", "name", "email");
				do {
					System.out.format(format, rs.getInt("id"), rs.getString("name"), rs.getString("email"));
				} while (rs.next());
				return true;
			} else {
				System.out.println("No results on display id customer");
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
	 * @see ro.sci.eshop.CustomerDao#getAllCustomers()
	 */
	@Override
	public int getAllCustomers() {
		Connection conn = getDbConnection();
		if (conn == null)
			return 0;
		PreparedStatement ps = null;
		ResultSet rs = null;

		final String format = "%2s%20s%32s\n";
		int nrOfCustomers = 0;
		System.out.println("---Display all customers");
		try {
			String sqlQuery = "select * from customer";
			ps = conn.prepareStatement(sqlQuery);
			rs = ps.executeQuery();

			boolean hasResults = rs.next();
			if (hasResults) {
				System.out.format(format, "id", "name", "email");
				do {
					System.out.format(format, rs.getInt("id"), rs.getString("name"), rs.getString("email"));
					nrOfCustomers++;
				} while (rs.next());
			} else {
				System.out.println("No results on display all customers");
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
		return nrOfCustomers;
	}
}
