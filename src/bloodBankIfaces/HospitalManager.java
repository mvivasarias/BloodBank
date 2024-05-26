package bloodBankIfaces;

import java.sql.Date;
import java.util.List;

import bloodBankPOJOs.Hospital;
import bloodBankPOJOs.Request;

public interface HospitalManager {

	public void addHospital(Hospital hospitalToAdd);

	public void deleteHospital(String name); 

	public List<Request>getRequestsOfHospitalByName(String name); 

	public Hospital searchHospitalById(Integer id); 

	public void addRequest(Integer hospitalId, Integer bloodId, float liters,Date date);
	
	public Hospital searchHospitalByEmail( String email);
}
