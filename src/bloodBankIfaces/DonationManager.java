package bloodBankIfaces;


import java.util.List;
import bloodBankPOJOs.Donation;

public interface DonationManager {
	public List<Donation> getDonationsByDate();
	
	public Donation addDonation (Donation donation);
	
	public void deleteDonationByBloodType(String bloodType);
	
	public void deleteDonationById(Integer donationId);
	
	public void addDonationBlood(Integer donationID, Integer bloodID);
	
	public List<Donation> getDonationsOfaPersonal(Integer id);
	
	 public List<Donation> getDonationsByBloodType(String bloodType);

}
