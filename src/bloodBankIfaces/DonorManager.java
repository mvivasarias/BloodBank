package bloodBankIfaces;

import bloodBankPOJOs.Donor;

public interface DonorManager {
	
	public void registerDonor(DonorManager donorRegistered);
	public void addDonor(DonorManager donorToAdd);
	public void deleteDonor(DonorManager donorToDelete);
	public Donor getDonorByID(int id);

	



}
