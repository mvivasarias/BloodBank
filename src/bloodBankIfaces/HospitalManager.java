package bloodBankIfaces;

import java.util.List;

import bloodBankPOJOs.Hospital;
import bloodBankPOJOs.Request;
import bloodBankPOJOs.Stock;

public interface HospitalManager {

	public void addHospital(Hospital hospitalToAdd);

	public void deleteHospital(String name);

	public void requestForBlood(Request bloodRequest);

	public List<Request> getRequestsOfHospital(String name);

	public Hospital searchHospitalById(Integer id);
}
