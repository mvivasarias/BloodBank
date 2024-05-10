package bloodBankJDBC;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCManager {

	private Connection c = null;

	public JDBCManager() {

		try {

			Class.forName("org.sqlite.JDBC");
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

			String sql = "CREATE TABLE contract (" 
						+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
						+ "salary INTEGER, hours INTEGER, " 
						+ "typeofwork TEXT NOT NULL);";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE personal (" 
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "name TEXT NOT NULL, surname  TEXT NOT NULL" 
					+ "phone INETGER, work_type TEXT NOT NULL"
					+ "contract_id INTEGER REFERENCES contract(id) );";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE blood (" 
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT," 
					+ "type TEXT NOT NULL"
					+ "stock_id INTEGER REFERENCES stock(id) );";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE donation (" 
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT," 
					+ "date DATE, amount INTEGER"
					+ "donor_id INTEGER REFERENCES donor(id) );";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE donor (" 
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "name TEXT NOT NULL,surname TEXT NOT NULL " 
					+ "dob DATE, bloodtype TEXT NOR NULL, times INTEGER);";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE hospital (" 
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "name TEXT NOT NULL,address TEXT NOT NULL); ";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE stock (" 
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT," 
					+ "date DATE, liters TEXT NOT NULL); ";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE donation_blood (" 
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT," 
					+ "donation_id INTEGER REFERENCES donation(id)"
					+ "blood_id INTEGER REFERENCES blood(id));";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE hospital_blood (" 
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT," 
					+ "hospital_id INTEGER REFERENCES hospital(id)"
					+ "blood_id INTEGER REFERENCES blood(id));";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE donation_personal(" 
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT," 
					+ "donation_id INTEGER REFERENCES donation(id)"
					+ "personal_id INTEGER REFERENCES personal(id));";
			stmt.executeUpdate(sql);

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
