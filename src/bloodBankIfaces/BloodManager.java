package bloodBankIfaces;
import java.util.List;

import bloodBankPOJOs.Blood;


public interface BloodManager {
	public List<Blood> getBloodList();
	public String getBloodtype();
	public void deleteBlood(Blood bloodToDelete);
	public Blood searchBloodById(Integer bloodId);
	public List<Blood> searchBloodType(String bloodType);
	public void updateBloodLiters(String bloodType,float liters);

}
