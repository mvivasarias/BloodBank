package bloodBankIfaces;

import java.sql.Date;
import java.util.List;

import bloodBankPOJOs.Hospital;
import bloodBankPOJOs.Request;
import bloodBankPOJOs.Stock;

public interface HospitalManager {

	public void addHospital(Hospital hospitalToAdd); // sql done

	public void deleteHospital(String name); // sql done

	public List<Request> getRequestsOfHospital(String name); // sql done

	public Hospital searchHospitalById(Integer id); // sql done

	public void addRequest(int hospitalId, int bloodId, float liters, Date date);
}
