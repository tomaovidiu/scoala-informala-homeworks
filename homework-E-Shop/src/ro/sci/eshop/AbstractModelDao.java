package ro.sci.eshop;

import java.sql.Connection;

public abstract class AbstractModelDao {

	protected static Connection getDbConnection() {
		loadDriver();

		Connection conn = ConnectionManager.getConnection("postgresql", "localhost", 5432, "eshop", "postgres",
				"cercetare");
		return conn;

	}

	private static void loadDriver() {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
