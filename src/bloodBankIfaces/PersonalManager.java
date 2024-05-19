package bloodBankIfaces;

import java.util.List;

import bloodBankPOJOs.Personal;

public interface PersonalManager {
	public void addPersonal(Personal personalToAdd); //sql done

	public void modifyPersonal(Personal personalToModify, String newName, String newSurname, String newEmail,
			String newPhoto); //sql done

	public Personal searchPersonalByEmail(String emailSearch); //sql done
	public Personal searchPersonalByID(Integer id);

	public void deletePersonalByID(Integer id); //sql done
	
	public boolean isPersonalTableNotEmpty();
	
	public List <Personal> listPersonal();

}
