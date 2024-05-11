package bloodBankJDBC;

import bloodBankIfaces.PersonalManager;
import bloodBankPOJOs.Personal;

public class JDBCPersonalManager implements PersonalManager {
	private JDBCManager manager;

	public JDBCPersonalManager(JDBCManager m) {
		this.manager = m;
	}

	@Override
	public void addPersonal(Personal personalToADD) {
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

	@Override
	public Personal searchPersonalBySurname(String PersonsurnameToDelete) { //TODO search person by surname to delete it
		// TODO Auto-generated method stub
		Personal personDeleted= null;
		return personDeleted;
	}

}
