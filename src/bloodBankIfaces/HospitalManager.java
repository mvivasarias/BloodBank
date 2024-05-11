package bloodBankIfaces;

import java.util.List;

import bloodBankPOJOs.Hospital;
import bloodBankPOJOs.Request;
import bloodBankPOJOs.Stock;

public interface HospitalManager {

	public void addHospital(Hospital hospitalToAdd);

	public void deleteHospital(String nameOfHospital);

	public void requestForBlood(Request bloodRequest);

	public List <Request> getRequestsOfHospital(String name);
}
