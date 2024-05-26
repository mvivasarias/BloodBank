package bloodBankJDBC;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCManager {

	private Connection c = null;

	public JDBCManager() {

		try {

			Class.forName("org.sqlite.JDBC"); // establish a connection with the database
			c = DriverManager.getConnection("jdbc:sqlite:./db/DataBase.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");

			System.out.print("Database Connection opened.");
			this.createTables();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.print("Libraries not loaded");
		}
	}

	private void createTables() {
		try {

			Statement stmt = c.createStatement();

			String sql = "CREATE TABLE contract (" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "salary INTEGER, "
					+ "hours INTEGER" + ");";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE personal (" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT NOT NULL,"
					+ "surname TEXT NOT NULL," + "email TEXT NOT NULL," + "foto BLOB," + "contract_id INTEGER,"
					+ "FOREIGN KEY(contract_id) REFERENCES contract(id)ON DELETE SET NULL" + ");";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE blood (" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "bloodType TEXT NOT NULL,"
					+ "liters REAL NOT NULL," + "date DATE NOT NULL" + ")";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE donation (" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "date DATE NOT NULL,"
					+ "amount REAL," + "donor_id INTEGER REFERENCES donor(id),"
					+ "personal_id INTEGER REFERENCES personal(id),"
					+ "FOREIGN KEY(donor_id) REFERENCES donor(id)ON DELETE SET NULL,"
					+ "FOREIGN KEY(personal_id) REFERENCES personal(id) ON DELETE SET NULL" + ");";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE donor (" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT NOT NULL,"
					+ "surname TEXT NOT NULL, " + "dob DATE NOT NULL," + "bloodtype TEXT NOT NULL, " + "times INTEGER"
					+ ");";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE hospital (" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT NOT NULL,"
					+ "address TEXT NOT NULL, " + "email TEXT NOT NULL UNIQUE" + "); ";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE donation_blood (" // DONATION GETS BLOOD N-N
					+ "donation_id INTEGER," + "blood_id INTEGER,"
					+ "FOREIGN KEY(donation_id) REFERENCES donation(id) ON DELETE CASCADE,"
					+ "FOREIGN KEY(blood_id) REFERENCES blood(id) ON DELETE CASCADE,"
					+ "PRIMARY KEY(donation_id, blood_id)" + ");";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE hospital_blood (" // REQUEST TABLE N-N
					+ "hospital_id INTEGER," + "blood_id INTEGER," + "liters REAL," + "date DATE,"
					+ "FOREIGN KEY(hospital_id) REFERENCES hospital(id) ON DELETE CASCADE,"
					+ "FOREIGN KEY(blood_id) REFERENCES blood(id) ON DELETE CASCADE,"
					+ "PRIMARY KEY(hospital_id, blood_id, date)" + ");";

			stmt.executeUpdate(sql);
			stmt.close();

		} catch (SQLException e) {
			if (!e.getMessage().contains("already exists")) {
				e.printStackTrace();
			}
		}

	}

	public Connection getConnection() {
		return c;
	}

	public void disconnect() {
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
