package bloodBankIfaces;
import java.util.List;

import bloodBankPOJOs.Blood;


public interface BloodManager {
	public List<Blood> getBloodList(); 
	public String getBloodtype();
	public void deleteBlood(Blood bloodToDelete);
	public Blood searchBloodById(Integer bloodId); //sql done
	public List<Blood> searchBloodType(String bloodType);//sql done
	public void updateBloodLiters(String bloodType,float liters);// sql done

}
