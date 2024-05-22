package bloodBankIfaces;

import java.io.File;

import bloodBankPOJOs.Blood;

public interface XMLManager {
	
	public void personal2xml(Integer id);
	public Blood xml2Blood (File xml);

}
