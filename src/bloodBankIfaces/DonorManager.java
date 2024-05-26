package bloodBankIfaces;

import java.util.List;

import bloodBankPOJOs.Donor;

public interface DonorManager {

	public void addDonor(Donor donorToAdd);

	public void deleteDonor(Integer id);

	public void modifyDonor(Donor donorModifying);

	public Donor getDonorByID(Integer id);

	public Donor searchDonorByNameSurnameBloodtype(String nameDonor, String surname, String bloodtype);

	public void incrementDonorTimes(Donor donorDonating);

	public List<Donor> listDonorsByName();

}
