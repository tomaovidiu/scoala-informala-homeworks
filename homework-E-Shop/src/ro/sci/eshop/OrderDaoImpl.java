package ro.sci.eshop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Implementation of orderDao interface.
 * 
 * @author Ovidiu
 *
 */
public class OrderDaoImpl extends AbstractModelDao implements OrderDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.eshop.orderDao#createorder(int, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean createOrder(int id, long value, Date date, boolean confirmed, int id_customer) {
		Connection conn = getDbConnection();
		if (conn == null)
			return false;
		System.out.println("---Create a order");
		String sqlQuery = " INSERT INTO public.orders(id, value, date, confirmed, id_customer) VALUES (?, ?, ?,?,?)";
		try (PreparedStatement ps = conn.prepareStatement(sqlQuery);) {
			ps.setInt(1, id);
			ps.setLong(2, value);
			ps.setDate(3, new java.sql.Date(date.getTime()));
			ps.setBoolean(4, confirmed);
			ps.setInt(5, id_customer);
			int rs = ps.executeUpdate();
			// System.out.println("rs " + rs);
			if (rs == 1) {
				System.out.println("order " + id + " added!");
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
	 * @see ro.sci.eshop.orderDao#deleteorder(int)
	 */
	@Override
	public boolean deleteOrder(int orderId) {
		Connection conn = getDbConnection();
		if (conn == null)
			return false;
		PreparedStatement ps = null;
		int deletedSuccesfull = 0;
		try {
			String sqlQuery = "delete from orders where id=?";
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, orderId);
			deletedSuccesfull = ps.executeUpdate();
			System.out.println("---Delete a order");
			if (deletedSuccesfull == 1) {
				System.out.println("order id " + orderId + " deleted !");
				return true;
			} else {
				System.out.println("Error: order NOT deleted");
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
	 * @see ro.sci.eshop.orderDao#getorderById(int)
	 */
	@Override
	public boolean getOrderById(int orderId) {
		Connection conn = getDbConnection();

		if (conn == null)
			return false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		final String format = "%2s%20s%32s\n";
		System.out.println("---Display a order");

		try {
			String sqlQuery = "select * from orders where id=?";
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, orderId); // first order
			rs = ps.executeQuery();
			boolean hasResults = rs.next();
			if (hasResults) {
				System.out.println("Display Id order: " + orderId);
				System.out.format(format, "id", "value", "date", "confirmed", "id_customer");
				System.out.format(format, rs.getInt("id"), rs.getFloat("value"), rs.getDate("date"),
						rs.getBoolean("confirmed"), rs.getInt("id_customer"));
				return true;
			} else {
				System.out.println("No results on display id order");
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
	 * @see ro.sci.eshop.orderDao#getAllorders()
	 */
	@Override
	public int getAllOrders() {
		Connection conn = getDbConnection();
		if (conn == null)
			return 0;
		PreparedStatement ps = null;
		ResultSet rs = null;

		final String format = "%2s%20s%32s%20s%20s\n";
		int nrOfOrders = 0;
		System.out.println("---Display all orders");
		try {
			String sqlQuery = "select * from orders";
			ps = conn.prepareStatement(sqlQuery);
			rs = ps.executeQuery();

			boolean hasResults = rs.next();
			if (hasResults) {
				System.out.format(format, "id", "value", "date", "confirmed", "id_customer");
				do {
					System.out.format(format, rs.getInt("id"), rs.getFloat("value"), rs.getDate("date"),
							rs.getBoolean("confirmed"), rs.getInt("id_customer"));
					nrOfOrders++;
				} while (rs.next());
			} else {
				System.out.println("No results on display all orders");
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
		return nrOfOrders;
	}
}
