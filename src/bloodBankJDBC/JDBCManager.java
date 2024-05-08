package bloodBankJDBC;

	import java.beans.Statement;
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
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			System.out.print("Libraries not loaded");
		}
	}
	
	private void createTables() {
		try {
			
			Statement stmt = c.createStatement();
			
			String sql = "CREATE TABLE owners ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "name text NOT NULL, phone INTEGER, "
					+ "cardnumber TEXT, email TEXT NOT NULL);";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE pets ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "name TEXT NOT NULL, cured BOOLEAN, "
					+ "typeOfAnimal TEXT CHECK (typeOfAnimal in (dog, cat, bird, hamster)),"
					+ "coat TEXT, dob DATE NOT NULL, onwer_id INTEGER REFERENCES owners(id));";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE vets(id INTEGER PRIMARY KEY  AUTOINCREMENT,"
					+"name TEXT NOT NULL, specialty TEXT, phone INTEGER,"
					+ "email TEXT NOT NULL, licence TEXT);";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE treats (vet_id INTEGER REFERENCES vets(id),"
					+ "pet_id REFERENCES pets(id), PRIMARY KEY(vet_id,pet_id) );";
			stmt.executeUpdate(sql);
			
			
		}catch(SQLException e) {
			if(!e.getMessage().contains("already exists")) 
			{
				e.printStackTrace();
			}			
		}
		
	}
	
	public Connection getConnection() {
		return c;
	}
	
	public void disconnect()
	{
		try {
			c.close();
		}
		catch(SQLException e){ 
			e.printStackTrace();
		}		
	}
}

