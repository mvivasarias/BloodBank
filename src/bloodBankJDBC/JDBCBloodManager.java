package bloodBankJDBC;

import java.sql.Date;
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
	public void deleteBlood(Blood bloodToDelete) {
		// TODO Auto-generated method stub
		// DELETE BLOOD FROM TABLE
	}

	@Override
	public Blood searchBloodById(Integer bloodId) {
		Blood blood = null;

		try {
			String sql = "SELECT * FROM blood WHERE id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, bloodId);
			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				String type = rs.getString("type");
				float liters = rs.getFloat("liters");
				Date date = rs.getDate("date");

				blood = new Blood();
				blood.setId(bloodId);
				blood.setBloodType(type);
				blood.setLiters(liters);
				blood.setDate(date);
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
			String sql = "SELECT * FROM blood WHERE type = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, bloodType);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				float liters = rs.getFloat("liters");
				Date date = rs.getDate("date");

				Blood blood = new Blood();
				blood.setId(id);
				blood.setBloodType(bloodType);
				blood.setLiters(liters);
				blood.setDate(date);

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
	public void addBlood(Blood newBlood) {

		try {
			String sql = "INSERT INTO blood (type, liters, date) VALUES (?, ?, ?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, newBlood.getBloodType());
			prep.setFloat(2, newBlood.getLiters());
			prep.setDate(3, new java.sql.Date(newBlood.getDate().getTime()));
			prep.executeUpdate();
			prep.close();
			System.out.println("Blood record added successfully.");
		} catch (SQLException e) {
			System.err.println("Error adding blood record: " + e.getMessage());
		}

	}

}
