package bloodBankJDBC;

import bloodBankIfaces.ContractManager;

public class JDBCContractManager implements ContractManager {

	private JDBCManager manager;

	public JDBCContractManager(JDBCManager m) {
		this.manager = m;
	}
	// metodos

}
