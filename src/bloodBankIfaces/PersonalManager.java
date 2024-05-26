package bloodBankIfaces;

import java.util.List;

import bloodBankPOJOs.Personal;

public interface PersonalManager {
	
	public void addPersonal(Personal personalToAdd); 

	public void modifyPersonal(Personal personalToModify, String newName, String newSurname, String Email,
			byte[] newPhoto); 

	public Personal searchPersonalByEmail(String emailSearch);

	public Personal searchPersonalByID(Integer id);

	public void deletePersonalByID(Integer id);

	public boolean isPersonalTableNotEmpty();

	public List<Personal> listPersonal();

}
