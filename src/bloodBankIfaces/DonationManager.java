package bloodBankIfaces;

import java.sql.Date;
import java.util.List;
import bloodBankPOJOs.Donation;

public interface DonationManager {
	public List<Donation> getDonationsByDate();
	
	public void addDonation (Donation donation);

	public Donation searchDonationById(Integer id);

	public Donation searchDonationByDate(Date date);
	
	public void deleteDonationByBloodType(String bloodType);
	
	public void deleteDonationById(int donationId);
	
	public void addDonationBlood(Integer donationID, Integer bloodID);

}
