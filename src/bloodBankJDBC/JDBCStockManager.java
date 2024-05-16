package bloodBankJDBC;

import java.beans.Statement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import bloodBankIfaces.StockManager;
import bloodBankPOJOs.Stock;

public class JDBCStockManager implements StockManager {

	private JDBCManager manager;

	public JDBCStockManager(JDBCManager m) {
		this.manager = m;
	}

	@Override
	public List<Stock> getListOfStock() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stock searchStockByID(Integer stock_id) {
		Stock stock = null;

		try {
			String sql = "SELECT id, date, liters FROM stock WHERE id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, stock_id);

			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				Date date = rs.getDate("date");
				float liters = rs.getFloat("liters");

				stock = new Stock(stock_id, date, liters);
			}

			rs.close();
			prep.close();
		} catch (SQLException e) {
			System.err.println("Error searching stock by ID: " + e.getMessage());
		}

		return stock;

	}

	@Override
	public float getTotalLitersAvailable(String bloodType) {
		float totalLitersAvailable = 0;

		try {
			String sql = "SELECT SUM(s.liters) AS total_liters " + "FROM stock s "
					+ "JOIN blood b ON s.id = b.stock_id " + "WHERE b.type = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, bloodType);

			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				totalLitersAvailable = rs.getFloat("total_liters");
			}

			rs.close();
			prep.close();
		} catch (SQLException e) {
			System.err.println("Error getting total liters available: " + e.getMessage());
		}

		return totalLitersAvailable;

	}

	@Override
	public void addStock(Stock stock) {
		try {

			String sql = "INSERT INTO stock (date, liters) VALUES (?, ?)";

			PreparedStatement prep = manager.getConnection().prepareStatement(sql);

			prep.setDate(1, new java.sql.Date(stock.getDate().getTime()));
			prep.setFloat(2, stock.getLiters());

			prep.executeUpdate();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	 public int getLastInsertedStockId() {
	        int lastInsertedId = -1; // Default 
	        try {
	            
	            String sql = "SELECT last_insert_rowid()";
	            PreparedStatement prep = manager.getConnection().prepareStatement(sql);
	           

	            // Execute the query
	            ResultSet resultSet = prep.executeQuery(sql);

	            // Check if the result set contains a row
	            if (resultSet.next()) {
	                // Retrieve the last inserted ID
	                lastInsertedId = resultSet.getInt(1);
	            }

	            resultSet.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return lastInsertedId;
	    }

}
