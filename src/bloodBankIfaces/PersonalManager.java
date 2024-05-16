package bloodBankIfaces;

import bloodBankPOJOs.Personal;

public interface PersonalManager {
	public void addPersonal(Personal personalToAdd); //sql done

	public void modifyPersonal(Personal personalToModify, String newName, String newSurname, String newEmail,
			String newPhoto); //sql done

	public Personal searchPersonalByEmail(String emailSearch); //sql done

	public void deletePersonalByID(Integer id); //sql done
	
	public boolean isPersonalTableNotEmpty();

}
