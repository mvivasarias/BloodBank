package bloodBankJDBC;

import bloodBankIfaces.PersonalManager;
import bloodBankPOJOs.Personal;

public class JDBCPersonalManager implements PersonalManager {
	private JDBCManager manager;

	public JDBCPersonalManager(JDBCManager m) {
		this.manager = m;
	}

	@Override
	public void registerPersonal(Personal personalToRegister) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifyPersonal(Personal personalToModify) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePersonal(Personal personalToDelete) {
		// TODO Auto-generated method stub

	}

}
