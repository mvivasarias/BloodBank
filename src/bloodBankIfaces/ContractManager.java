package bloodBankIfaces;

import bloodBankPOJOs.Contract;

public interface ContractManager {
	
	public void addContract(Contract addContract); //sql done
	public Contract searchContractById(Integer contractId); //sql done
	

}
