package bloodBankIfaces;
import java.util.List;

import bloodBankPOJOs.Blood;


public interface BloodManager {
	
	public void deleteBloodById(Integer bloodId);
	
	public Blood searchBloodById(Integer bloodId);
	
	public List<Blood> searchBloodType(String bloodType);
	
	public Blood addBlood(Blood newBlood);
	
	public float getTotalLitersAvailable(String bloodType); 
	
	public void updateStockLitersById(Integer blood_id, float newLiters);
	
	public List<Blood> getBloodListByType(); 

}
