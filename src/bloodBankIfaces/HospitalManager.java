package bloodBankIfaces;

import java.sql.Date;
import java.util.List;

import bloodBankPOJOs.Hospital;
import bloodBankPOJOs.Request;

public interface HospitalManager {

	public void addHospital(Hospital hospitalToAdd); // sql done

	public void deleteHospital(String name); // sql done

	public List<Request>getRequestsOfHospitalByName(String name); // sql done

	public Hospital searchHospitalById(Integer id); // sql done

	public void addRequest(Integer hospitalId, Integer bloodId, float liters,Date date);
	
	public Hospital searchHospitalByEmail( String email);
}
