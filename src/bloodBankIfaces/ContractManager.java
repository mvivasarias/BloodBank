package bloodBankIfaces;

import bloodBankPOJOs.Contract;
import bloodBankPOJOs.Personal;

public interface ContractManager {
	
	public void addContract(Contract addContract); //sql done
	public Contract searchContractById(int contractId); //sql done
	

}
