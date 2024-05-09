package bloodBankIfaces;

import bloodBankPOJOs.Personal;

public interface PersonalManager {
	public void registerPersonal(Personal personalToRegister);

	public void modifyPersonal(Personal personalToModify);

	public void deletePersonal(Personal personalToDelete);

}
