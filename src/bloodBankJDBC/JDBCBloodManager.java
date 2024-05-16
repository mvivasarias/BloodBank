package bloodBankJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bloodBankIfaces.BloodManager;
import bloodBankPOJOs.Blood;
import bloodBankPOJOs.Stock;

public class JDBCBloodManager implements BloodManager {

	private JDBCManager manager;
	private JDBCStockManager stockManager;

	public JDBCBloodManager(JDBCManager m) {
		this.manager = m;
	}

	@Override
	public List<Blood> getBloodList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBloodtype() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBlood(Blood bloodToDelete) {
		// TODO Auto-generated method stub
		// DELETE BLOOD FROM TABLE
	}

	@Override
	public Blood searchBloodById(Integer bloodId) {
		Blood blood = null;

		try {
			String sql = "SELECT id, type, stock_id FROM blood WHERE id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, bloodId);

			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				String type = rs.getString("type");
				int stock_id = rs.getInt("stock_id");

				Stock stock = stockManager.searchStockByID(stock_id);

				blood = new Blood(bloodId, type, stock);
			}

			rs.close();
			prep.close();
		} catch (SQLException e) {
			System.err.println("Error searching blood by ID: " + e.getMessage());
		}

		return blood;
	}

	@Override
	public List<Blood> searchBloodType(String bloodType) {
		List<Blood> bloodList = new ArrayList<>();

		try {
			String sql = "SELECT id, type, stock_id FROM blood WHERE type = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, bloodType);

			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int stock_id = rs.getInt("stock_id");
				Stock stock = stockManager.searchStockByID(stock_id);

				Blood blood = new Blood(id, bloodType, stock);
				bloodList.add(blood);
			}

			rs.close();
			prep.close();
		} catch (SQLException e) {
			System.err.println("Error searching blood by type: " + e.getMessage());
		}

		return bloodList;

	}



	@Override
	public void addBloodAndStock(Blood newBlood, Stock stock) {

		try {
			stockManager.addStock(stock);
			int stockId = stockManager.getLastInsertedStockId();

			Stock stockToAdd = stockManager.searchStockByID(stockId);

			// If the retrievedStock is not null, proceed to add the blood record
			if (stockToAdd != null) {
				// Add the blood record with the retrieved stock ID
				String insertBloodSql = "INSERT INTO blood (type, stock_id) VALUES (?, ?)";
				PreparedStatement prep = manager.getConnection().prepareStatement(insertBloodSql);
				prep.setString(1, newBlood.getType());
				prep.setInt(2, stockId);
				prep.executeUpdate();
				prep.close();
			} else {
				System.out.println("Stock can´t be found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
