package bloodBankJDBC;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bloodBankIfaces.HospitalManager;
import bloodBankPOJOs.Blood;
import bloodBankPOJOs.Contract;
import bloodBankPOJOs.Hospital;
import bloodBankPOJOs.Personal;
import bloodBankPOJOs.Request;
import bloodBankPOJOs.Stock;

public class JDBCHospitalManager implements HospitalManager {
	private JDBCManager manager;
	private JDBCBloodManager bloodManager;

	public JDBCHospitalManager(JDBCManager m) {
		this.manager = m;
	}

	@Override
	public void addHospital(Hospital hospitalToAdd) { // ADD TO THE TABLE THE HOSPITAL WITH ALL THAT VALUES
		try {
			String sql = "INSERT INTO hospital (name, address, email) VALUES (?, ?, ?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, hospitalToAdd.getName());
			prep.setString(2, hospitalToAdd.getAddress());
			prep.setString(3, hospitalToAdd.getEmail());

			prep.executeUpdate();

			System.out.println("Hospital added successfully");
		} catch (SQLException e) {
			System.err.println("Error adding hospital: " + e.getMessage());
		}

	}

	@Override
	public void deleteHospital(String name) { // delete the hospital with that name
		try {
			String sql = "DELETE FROM hospital WHERE name = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, name);

			int rowsAffected = prep.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Hospital deleted successfully");
			} else {
				System.out.println("No hospital found with the specified name.");
			}
		} catch (SQLException e) {
			System.err.println("Error deleting hospital: " + e.getMessage());
		}
	}

	@Override
	public List<Request> getRequestsOfHospitalByName (String hospitalName) {
		List<Request> requests = new ArrayList<>();
		try {
			String sql = "SELECT hb.hospital_id, hb.blood_id, hb.date, hb.liters " + "FROM hospital_blood hb "
					+ "JOIN hospital h ON hb.hospital_id = h.id " + "WHERE h.name = ?";

			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, hospitalName);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				int hospitalId = rs.getInt("hospital_id");
				int bloodId = rs.getInt("blood_id");
				Date date = rs.getDate("date");
				float liters = rs.getFloat("liters");
				Hospital hospital = searchHospitalById(hospitalId);
				Blood blood = bloodManager.searchBloodById(bloodId);

				requests.add(new Request(hospital, blood, liters, date));
			}

			rs.close();
			prep.close();
		} catch (SQLException e) {
			System.err.println("Error fetching requests: " + e.getMessage());
		}

		return requests;
	}

	public Hospital searchHospitalById(Integer id) {
		Hospital hospital = null;

		try {
			String sql = "SELECT id, name, address, email FROM hospital WHERE id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, id);

			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				String name = rs.getString("name");
				String address = rs.getString("address");
				String email = rs.getString("email");

				hospital = new Hospital(id, name, address, email);
			}

			rs.close();
			prep.close();
		} catch (SQLException e) {
			System.err.println("Error searching hospital by ID: " + e.getMessage());
		}
		return hospital;
	}

	public void addRequest(int hospital_id, int blood_id, float liters, Date date) {
		try {
			String sql = "INSERT INTO hospital_blood (hospital_id, blood_id, liters, date_id) VALUES (?, ?, ?, ?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, hospital_id);
			prep.setInt(2, blood_id);
			prep.setFloat(3, liters);
			prep.setDate(4, new java.sql.Date(date.getTime()));
			int rowsAffected = prep.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Request added successfully to hospital_blood table.");
			} else {
				System.out.println("Failed to add request to hospital_blood table.");
			}

			prep.close();
		} catch (SQLException e) {
			System.err.println("Error adding request to hospital_blood table: " + e.getMessage());
		}
	}

	@Override
	public Hospital searchHospitalByEmail(String email) {
		Hospital hospital = null;

		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM hospital WHERE email = ?";

			ResultSet rs = stmt.executeQuery(sql);

			Integer hospital_id = rs.getInt("id");
			String name = rs.getString("name");
			String address = rs.getString("address");
			String email_hosp = rs.getString("email");

			hospital = new Hospital(hospital_id, name, address, email_hosp);

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			System.err.println("Hospital with this email has not been found " + e.getMessage());
			e.printStackTrace();
		}
		return hospital;

	}

}
