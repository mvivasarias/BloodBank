package bloodBankJDBC;

import java.sql.Date;
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

}
