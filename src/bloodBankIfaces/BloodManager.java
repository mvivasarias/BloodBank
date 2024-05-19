package bloodBankIfaces;
import java.util.List;

import bloodBankPOJOs.Blood;
import bloodBankPOJOs.Stock;


public interface BloodManager {
	public List<Blood> getBloodList(); 
	public void deleteBloodById(int bloodId);//sql done
	public Blood searchBloodById(Integer bloodId); //sql done
	public List<Blood> searchBloodType(String bloodType);//sql done
	public void addBlood(Blood newBlood);//sql done
	public float getTotalLitersAvailable(String bloodType); //Sql done
	public void updateStockLitersById(int blood_id, float newLiters);//sql done

}
