package bloodBankJDBC;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bloodBankIfaces.BloodManager;
import bloodBankPOJOs.Blood;

public class JDBCBloodManager implements BloodManager {

	private JDBCManager manager;

	public JDBCBloodManager(JDBCManager m) {
		this.manager = m;
	}

	@Override
	public void deleteBloodById(Integer bloodId) {
		try {
			String sql = "DELETE FROM blood WHERE id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, bloodId);
			prep.executeUpdate();
			prep.close();
		} catch (SQLException e) {
			System.err.println("Error deleting blood by ID: " + e.getMessage());
		}

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
	public Blood addBlood(Blood newBlood) {
		Blood blood=null;

		try {
			String sql = "INSERT INTO blood (type, liters, date) VALUES (?, ?, ?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, newBlood.getBloodType());
			prep.setFloat(2, newBlood.getLiters());
			prep.setDate(3, new java.sql.Date(newBlood.getDate().getTime()));
			prep.executeUpdate();

			try (ResultSet generatedKeys = prep.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					int generatedId = generatedKeys.getInt(1); // Retrieve the generated ID
					blood= new Blood(generatedId,newBlood.getBloodType(), newBlood.getLiters(), newBlood.getDate()); // Set the generated ID in the Blood object
					System.out.println("Generated ID for blood record: " + generatedId);
				} else {
					throw new SQLException("Creating blood record failed, no ID obtained.");
				}
			}

			prep.close();
			System.out.println("Blood record added successfully.");
		} catch (SQLException e) {
			System.err.println("Error adding blood record: " + e.getMessage());
		}
		return blood;

	}
	

	public float getTotalLitersAvailable(String bloodType) {
		float totalLitersAvailable = 0;

		try {
			String sql = "SELECT SUM(liters) AS total_liters FROM blood WHERE type = ?";
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

	public void updateStockLitersById(Integer blood_id, float newLiters) {

		try {
			String sql = "UPDATE blood SET liters = ? WHERE id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);

			prep.setFloat(1, newLiters);
			prep.setInt(2, blood_id);
			prep.executeUpdate();
			prep.close();

		} catch (SQLException e) {
			System.err.println("Error updating blood record: " + e.getMessage());
		}

	}

	@Override
	public List<Blood> getBloodListByType() {
		List<Blood> bloods = new ArrayList<>();
		try {
			String sql = "SELECT * FROM blood ORDER BY bloodType ASC";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String bloodType = rs.getString("bloodType");
				float liters = rs.getFloat("liters");
				Date dob = rs.getDate("dob");

				Blood blood = new Blood(id, bloodType, liters, dob);
				bloods.add(blood);
			}

			rs.close();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bloods;
	}
}
