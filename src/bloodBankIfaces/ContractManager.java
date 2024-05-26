package bloodBankIfaces;

import bloodBankPOJOs.Contract;

public interface ContractManager {
	
	public Contract addContract(Contract addContract);
	public Contract searchContractById(Integer contractId);
	

}
