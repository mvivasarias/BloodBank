package bloodBankIfaces;

import bloodBankPOJOs.Personal;

public interface PersonalManager {
	public void addPersonal(Personal personalToAdd);

	public void modifyPersonal(Personal personalToModify);

	public void deletePersonal(Personal personalToDelete);

	public Personal searchPersonalBySurname(String surnameToDelete);

}
