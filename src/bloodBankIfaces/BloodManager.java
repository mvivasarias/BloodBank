package bloodBankIfaces;
import java.util.List;

import bloodBankPOJOs.Blood;


public interface BloodManager {
	public List<Blood> getBloodList();
	public String getBloodtype();

}
