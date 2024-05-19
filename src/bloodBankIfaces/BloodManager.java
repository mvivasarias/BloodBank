package bloodBankIfaces;
import java.util.List;

import bloodBankPOJOs.Blood;


public interface BloodManager {
	
	public void deleteBloodById(Integer bloodId);//sql done
	
	public Blood searchBloodById(Integer bloodId); //sql done
	
	public List<Blood> searchBloodType(String bloodType);//sql done
	
	public void addBlood(Blood newBlood);//sql done
	
	public float getTotalLitersAvailable(String bloodType); //Sql done
	
	public void updateStockLitersById(Integer blood_id, float newLiters);//sql done
	
	public List<Blood> getBloodListByType(); //sql done

}
