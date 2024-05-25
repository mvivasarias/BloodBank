package bloodBankJDBC;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bloodBankIfaces.DonorManager;
import bloodBankPOJOs.Donor;

public class JDBCDonorManager implements DonorManager {

	private JDBCManager manager;

	public JDBCDonorManager(JDBCManager m) {
		this.manager = m;
	}

	@Override
	public void deleteDonor(Integer id) {
		try {
			String sql = "DELETE FROM donor WHERE id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, id);

			int rowsDeleted = prep.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Donor deleted successfully.");

			}
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void addDonor(Donor donorToAdd) {
		

		try {
	
			String sql = "INSERT INTO donor (name, surname, dob, bloodtype, times) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);

			prep.setString(1, donorToAdd.getName());
			prep.setString(2, donorToAdd.getSurname());
			prep.setDate(3, donorToAdd.getDob()); //usa la fecha de nacimiento del donante
			prep.setString(4, donorToAdd.getBloodtype());
			prep.setInt(5, donorToAdd.getTimes());

			prep.executeUpdate();

			System.out.println("Donor added successfully.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void modifyDonor(Donor donorModifying) {
		try {
			String sql = "UPDATE donor SET name = ?, surname = ?, dob = ?, bloodtype = ? WHERE id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);

			prep.setString(1, donorModifying.getName());
			prep.setString(2, donorModifying.getSurname());
			prep.setDate(3, new java.sql.Date(donorModifying.getDob().getTime()));
			prep.setString(4, donorModifying.getBloodtype());

			int rowsUpdated = prep.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Donor updated successfully.");
			} else {
				System.out.println("Error updating donor.");
			}

			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Donor getDonorByID(Integer id) {
		Donor donor = null;

		try {

			String sql = "SELECT * FROM donor WHERE id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				Date dob = rs.getDate("dob");
				String bloodType = rs.getString("bloodtype");
				Integer times = rs.getInt("times");

				donor = new Donor(id, name, surname, dob, bloodType, times);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return donor;

	}

	@Override
	public Donor searchDonorByNameSurnameBloodtype(String nameDonor, String surname, String bloodType) {
		Donor donor = null;

		try {
			String sql = "SELECT * FROM donor WHERE name = ? AND surname = ? AND bloodType = ? ";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, nameDonor);
			prep.setString(2, surname);
			prep.setString(3, bloodType);
			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				Integer id = rs.getInt("id");
				Date dob = rs.getDate("dob");
				int times = rs.getInt("times");

				donor = new Donor(id, nameDonor, surname, dob, bloodType, times);
			}

			rs.close();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return donor;

	}
	
	@Override
	public void incrementDonorTimes(Donor donorDonating) {

		try {
			String sql = "UPDATE donor SET times = times + 1 WHERE id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);

			prep.setInt(1, donorDonating.getId());
			int rowsAffected = prep.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Donor " + donorDonating.getName() + " " + donorDonating.getSurname()
						+ " has donated blood " + (donorDonating.getTimes() + 1) + " times.");
			} else {
				System.out.println("Failed to increment donation times for donor " + donorDonating.getName() + " "
						+ donorDonating.getSurname());
			}

			prep.close();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Donor> listDonorsByName() {
		List<Donor> donors = new ArrayList<>();
		try {
			String sql = "SELECT * FROM donor ORDER BY name ASC";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				Date dob = rs.getDate("dob");
				String bloodType = rs.getString("bloodtype");
				Integer times = rs.getInt("times");

				Donor donor = new Donor(id, name, surname, dob, bloodType, times);
				donors.add(donor);
			}

			rs.close();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return donors;
	}

}
