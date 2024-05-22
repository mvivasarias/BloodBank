package bloodBankIfaces;

import java.io.File;
import bloodBankPOJOs.Donation;

public interface XMLManager {
	
	public void personal2xml(Integer id);
	public Donation xml2Donation(File xml);

}
