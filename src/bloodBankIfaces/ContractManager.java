package bloodBankIfaces;

import bloodBankPOJOs.Contract;

public interface ContractManager {
	
	public Contract addContract(Contract addContract); //sql done
	public Contract searchContractById(Integer contractId); //sql done
	

}
