package bloodBankIfaces;

import bloodBankPOJOs.Donor;

public interface DonorManager {
	
	public void addDonor(Donor donorToAdd);
	public void deleteDonor(Integer id);
	public void modifyDonor(Donor donorModifying);
	public Donor getDonorByID(Integer id);
	public Donor searchDonorByNameAndSurname(String nameDonor, String surname);
	public void incrementDonorTimes(Donor donorDonating);
		
	

	



}
