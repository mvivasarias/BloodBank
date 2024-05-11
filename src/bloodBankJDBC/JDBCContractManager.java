package bloodBankJDBC;

import bloodBankIfaces.ContractManager;
import bloodBankPOJOs.Contract;

public class JDBCContractManager implements ContractManager {

	private JDBCManager manager;

	public JDBCContractManager(JDBCManager m) {
		this.manager = m;
	}
	

	@Override
	public void addContract(Contract addContract) { //ADD A CONTRACT TO THE TABLE CONTRACT
		// TODO Auto-generated method stub
		
	}

}
