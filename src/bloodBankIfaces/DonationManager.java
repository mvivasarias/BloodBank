package bloodBankIfaces;

import java.sql.Date;
import java.util.List;
import bloodBankPOJOs.Donation;

public interface DonationManager {
	public List<Donation> getDonations();
	
	public void addDonation (Donation donation);

	public Donation searchDonationById(Integer id);

	public Donation searchDonationByDate(Date date);
	
	public void addDonationBlood(Integer donationID, Integer bloodID);

}
