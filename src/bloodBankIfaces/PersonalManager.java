package bloodBankIfaces;

import bloodBankPOJOs.Personal;

public interface PersonalManager {
	public void addPersonal(Personal personalToAdd);

	public void modifyPersonal(Personal personalToModify, String newName, String newSurname, String newEmail,
			String newPhoto);

	public void deletePersonal(Personal personalToDelete);

	public Personal searchPersonalByEmail(String emailSearch);

	public void deletePersonalByID(Integer id);

}
