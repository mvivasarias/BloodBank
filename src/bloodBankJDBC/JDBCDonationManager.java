package bloodBankJDBC;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bloodBankIfaces.DonationManager;
import bloodBankPOJOs.Donation;
import bloodBankPOJOs.Donor;
import bloodBankPOJOs.Personal;

public class JDBCDonationManager implements DonationManager {
	private JDBCManager manager;
	private JDBCDonorManager donorManager;
	private JDBCPersonalManager personalManager;

	public JDBCDonationManager(JDBCManager m) {
		this.manager = m;
	}


	@Override
	public void addDonation(Donation donation) {
		try {
			String sql = "INSERT INTO donation (date, amount, donor_id, personal_id) VALUES (?, ?, ?, ?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);

			prep.setDate(1, new java.sql.Date(donation.getDate().getTime())); // Assuming donation.getDate() returns a
																				// java.util.Date
			prep.setFloat(2, donation.getAmount());
			prep.setInt(3, donation.getDonor().getId());
			prep.setInt(4, donation.getPersonal().getId());

			prep.executeUpdate();

			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void addDonationBlood(Integer donationID, Integer bloodID) {
		try {

			String sql = "INSERT INTO donation_blood (donation_id, blood_id) VALUES (?, ?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);

			prep.setInt(1, donationID);
			prep.setInt(2, bloodID);

			prep.executeUpdate();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override

	public void deleteDonationById(Integer donationId) {

		try {
			String sql = "DELETE FROM donation WHERE id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, donationId);
			int affectedRows = prep.executeUpdate();

			if (affectedRows > 0) {
				System.out.println("Donation with ID " + donationId + " has been successfully deleted.");
			} else {
				System.out.println("No donation found with ID " + donationId + ".");
			}
		} catch (SQLException e) {
			System.err.println("Error deleting donation: " + e.getMessage());
		}
	}

	@Override

	public void deleteDonationByBloodType(String bloodType) {
		try {
			String sql = "DELETE FROM donation " + "WHERE id IN (SELECT donation_id "
					+ "FROM donation_blood JOIN blood ON donation_blood.blood_id = blood.id " + "WHERE blood.type = ?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, bloodType);
			int affectedRows = prep.executeUpdate();

			if (affectedRows > 0) {
				System.out.println("Donations with blood type " + bloodType + " have been successfully deleted.");
			} else {
				System.out.println("No donations found with blood type " + bloodType + ".");
			}
		} catch (SQLException e) {
			System.err.println("Error deleting donations: " + e.getMessage());
		}
	}

	@Override
	public List<Donation> getDonationsByDate() {
		List<Donation> donations = new ArrayList<>();
		try {
			String sql = "SELECT * FROM donation ORDER BY date";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				Date date = rs.getDate("date");
				float amount = rs.getFloat("amount");
				Donor donor = donorManager.getDonorByID(rs.getInt("donor_id"));
				Personal personal = personalManager.searchPersonalByID(rs.getInt("personal_id"));

				donations.add(new Donation(id, date, amount, donor, personal));
			}

			rs.close();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return donations;
	}

}
