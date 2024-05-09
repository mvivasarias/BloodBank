package bloodBankIfaces;

import java.sql.Date;
import java.util.List;
import bloodBankPOJOs.Donation;

public interface DonationManager {
	public List<Donation> getDonations();

	public Donation searchDonationById(Integer id);

	public Donation searchDonationByDate(Date date);

}
