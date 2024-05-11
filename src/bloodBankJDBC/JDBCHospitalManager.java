package bloodBankJDBC;

import java.util.ArrayList;
import java.util.List;


import bloodBankIfaces.HospitalManager;
import bloodBankPOJOs.Hospital;
import bloodBankPOJOs.Request;
import bloodBankPOJOs.Stock;

public class JDBCHospitalManager implements HospitalManager {
	private JDBCManager manager;

	public JDBCHospitalManager(JDBCManager m) {
		this.manager = m;
	}


	@Override
	public void requestForBlood(Request bloodRequest) { //add to the table request this request
		// TODO Auto-generated method stub
		

		
	}


	@Override
	public void addHospital(Hospital hospitalToAdd) { //ADD TO THE TABLE THE HOSPITAL WITH ALL THAT VALUES
		// TODO Auto-generated method stub
		
	}




	@Override
	public void deleteHospital(String nameOfHospital) { //delte the hospital with that name
		// TODO Auto-generated method stub
		
	}


	@Override
	public List <Request> getRequestsOfHospital(String name) {
		List<Request> requests = new ArrayList<Request>();
	 //get all the requests done by the hospital with that name
		// TODO Auto-generated method stub
		
		return requests;
		
	}


	

}
