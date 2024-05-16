package bloodBankJDBC;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import bloodBankIfaces.DonationManager;
import bloodBankPOJOs.Donation;

public class JDBCDonationManager implements DonationManager {
	private JDBCManager manager;

	public JDBCDonationManager(JDBCManager m) {
		this.manager = m;
	}

	@Override
	public List<Donation> getDonations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Donation searchDonationById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Donation searchDonationByDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addDonation(Donation donation) {
		try {
			String sql = "INSERT INTO donation (date, amount, donor_id, personal_id) VALUES (?, ?, ?, ?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);

			prep.setDate(1, new java.sql.Date(donation.getDate().getTime())); // Assuming donation.getDate() returns a
																				// java.util.Date
			prep.setInt(2, donation.getAmount());
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

			// Execute the statement
			prep.executeUpdate();

			// Close the PreparedStatement
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
