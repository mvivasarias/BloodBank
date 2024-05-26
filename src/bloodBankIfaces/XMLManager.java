package bloodBankIfaces;

import java.io.File;
import bloodBankPOJOs.Donation;
import bloodBankPOJOs.Personal;

public interface XMLManager {

	public void personal2xml(Integer id);

	public Donation xml2Donation(File xml);

	public void donation2xml(String bloodType);

	public Personal xml2Personal(File xml);

	public void personalTransformerToHTML(Integer id);

	public void donationTransformerToHTML(String bloodType);

}
