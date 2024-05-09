package bloodBankJDBC;

import bloodBankIfaces.HospitalManager;
import bloodBankPOJOs.Hospital;
import bloodBankPOJOs.Stock;

public class JDBCHospitalManager implements HospitalManager {
	private JDBCManager manager;

	public JDBCHospitalManager(JDBCManager m) {
		this.manager = m;
	}

	@Override
	public void registerUserHospital(Hospital userToRegister) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUserHospital(Hospital userToDelete) {
		// TODO Auto-generated method stub

	}

	@Override
	public void extractionForHospital(Stock stockValid, String typeOfBlood) {
		// TODO Auto-generated method stub

	}

}
