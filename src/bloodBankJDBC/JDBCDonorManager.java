package bloodBankJDBC;

import bloodBankIfaces.DonorManager;
import bloodBankPOJOs.Donor;

public class JDBCDonorManager implements DonorManager {

	private JDBCManager manager;

	public JDBCDonorManager(JDBCManager m) {
		this.manager = m;
	}

	@Override
	public void deleteDonor(DonorManager donorToDelete) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addDonor(DonorManager donorToAdd) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerDonor(DonorManager donorRegistered) {
		// TODO Auto-generated method stub

	}

	@Override
	public Donor getDonorByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
