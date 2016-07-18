package ro.sci.eshop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementation of OrderItemDao interface.
 * 
 * @author Ovidiu
 *
 */
public class OrderItemDaoImpl extends AbstractModelDao implements OrderItemDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.eshop.OrderItemDao#createOrderItem(int, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean createOrderItem(int id, int id_order, int quantity, int id_product) {
		Connection conn = getDbConnection();
		if (conn == null)
			return false;
		System.out.println("---Create a orderItem");
		String sqlQuery = " INSERT INTO public.orderItems(id, id_order, quantity, id_product) VALUES (?, ?, ?,?)";
		try (PreparedStatement ps = conn.prepareStatement(sqlQuery);) {
			ps.setInt(1, id);
			ps.setInt(2, id_order);
			ps.setInt(3, quantity);
			ps.setInt(4, id_product);
			int rs = ps.executeUpdate();
			// System.out.println("rs " + rs);
			if (rs == 1) {
				System.out.println("OrderItem " + id_order + " added!");
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
	 * @see ro.sci.eshop.OrderItemDao#deleteOrderItem(int)
	 */
	@Override
	public boolean deleteOrderItem(int orderItemId) {
		Connection conn = getDbConnection();
		if (conn == null)
			return false;
		PreparedStatement ps = null;
		int deletedSuccesfull = 0;
		try {
			String sqlQuery = "delete from orderItems where id=?";
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, orderItemId);
			deletedSuccesfull = ps.executeUpdate();
			System.out.println("---Delete a orderItem");
			if (deletedSuccesfull == 1) {
				System.out.println("OrderItem id " + orderItemId + " deleted !");
				return true;
			} else {
				System.out.println("Error: OrderItem NOT deleted");
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
	 * @see ro.sci.eshop.OrderItemDao#getOrderItemById(int)
	 */
	@Override
	public boolean getOrderItemById(int orderItemId) {
		Connection conn = getDbConnection();

		if (conn == null)
			return false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		final String format = "%2s%20s%32s\n";
		System.out.println("---Display a orderItem");

		try {
			String sqlQuery = "select * from orderItems where id=?";
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, orderItemId); // first orderItem
			rs = ps.executeQuery();
			boolean hasResults = rs.next();
			if (hasResults) {
				System.out.println("Display Id orderItem: " + orderItemId);
				System.out.format(format, "id", "id_order", "quantity", "id_product");
				do {
					System.out.format(format, rs.getInt("id"), rs.getInt("id_order"), rs.getInt("quantity"),
							rs.getInt("id_product"));
				} while (rs.next());
				return true;
			} else {
				System.out.println("No results on display id orderItem");
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
	 * @see ro.sci.eshop.OrderItemDao#getAllOrderItems()
	 */
	@Override
	public int getAllOrderItems() {
		Connection conn = getDbConnection();
		if (conn == null)
			return 0;
		PreparedStatement ps = null;
		ResultSet rs = null;

		final String format = "%2s%20s%32s%20s\n";
		int nrOfOrderItems = 0;
		System.out.println("---Display all orderItems");
		try {
			String sqlQuery = "select * from orderItems";
			ps = conn.prepareStatement(sqlQuery);
			rs = ps.executeQuery();

			boolean hasResults = rs.next();
			if (hasResults) {
				System.out.format(format, "id", "id_order", "quantity", "id_product");
				do {
					System.out.format(format, rs.getInt("id"), rs.getInt("id_order"), rs.getInt("quantity"),
							rs.getInt("id_product"));
					nrOfOrderItems++;
				} while (rs.next());
			} else {
				System.out.println("No results on display all orderItems");
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
		return nrOfOrderItems;
	}
}
