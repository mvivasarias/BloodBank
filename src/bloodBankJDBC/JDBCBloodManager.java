package bloodBankJDBC;

import java.util.List;
import bloodBankIfaces.BloodManager;
import bloodBankPOJOs.Blood;

public class JDBCBloodManager implements BloodManager {

	private JDBCManager manager;

	public JDBCBloodManager(JDBCManager m) {
		this.manager = m;
	}

	@Override
	public List<Blood> getBloodList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBloodtype() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBlood(Blood bloodToDelete) {
		// TODO Auto-generated method stub
		//DELETE BLOOD FROM TABLE
	}

}
