package bloodBankIfaces;


import java.util.List;
import bloodBankPOJOs.Donation;

public interface DonationManager {
	public List<Donation> getDonationsByDate();
	
	public void addDonation (Donation donation);
	
	public void deleteDonationByBloodType(String bloodType);
	
	public void deleteDonationById(Integer donationId);
	
	public void addDonationBlood(Integer donationID, Integer bloodID);

}
