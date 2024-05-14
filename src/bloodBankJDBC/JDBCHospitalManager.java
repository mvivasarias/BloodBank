package bloodBankJDBC;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import bloodBankIfaces.HospitalManager;
import bloodBankPOJOs.Blood;
import bloodBankPOJOs.Hospital;
import bloodBankPOJOs.Request;
import bloodBankPOJOs.Stock;

public class JDBCHospitalManager implements HospitalManager {
	private JDBCManager manager;
	private JDBCBloodManager bloodManager;

	public JDBCHospitalManager(JDBCManager m) {
		this.manager = m;
	}

	@Override
	public void addHospital(Hospital hospitalToAdd) { //ADD TO THE TABLE THE HOSPITAL WITH ALL THAT VALUES
		 try {
			 	String sql = "INSERT INTO hospital (name, address, email) VALUES (?, ?, ?)";
		        PreparedStatement prep = manager.getConnection().prepareStatement(sql);
		        prep.setString(1, hospitalToAdd.getName());
		        prep.setString(2, hospitalToAdd.getAddress());
		        prep.setString(3, hospitalToAdd.getEmail());
		        
		        prep.executeUpdate();
		        
		        System.out.println("Hospital added successfully");
		    } catch (SQLException e) {
		        System.err.println("Error adding hospital: " + e.getMessage());
		    }
		
	}




	@Override
	public void deleteHospital(String name) { //delete the hospital with that name
		 try {
		        String sql = "DELETE FROM hospital WHERE name = ?";
		        PreparedStatement prep = manager.getConnection().prepareStatement(sql);
		        prep.setString(1, name);
		        
		        int rowsAffected = prep.executeUpdate();
		        
		        if (rowsAffected > 0) {
		            System.out.println("Hospital deleted successfully");
		        } else {
		            System.out.println("No hospital found with the specified name.");
		        }
		    } catch (SQLException e) {
		        System.err.println("Error deleting hospital: " + e.getMessage());
		    }
		}
		



	@Override
	public List <Request> getRequestsOfHospital(String name) {
		List<Request> requests = new ArrayList<Request>();

		    try {
		    	String sql = "SELECT r.liters, r.date, b.id AS blood_id, h.id AS hospital_id " +
		                "FROM hospital_blood hb " +
		                "JOIN request r ON hb.hospital_id = r.hospital_id AND hb.date_id = r.date_id " +
		                "JOIN hospital h ON hb.hospital_id = h.id " +
		                "JOIN blood b ON hb.blood_id = b.id " +
		                "WHERE h.name = ?";
		        
		        PreparedStatement prep = manager.getConnection().prepareStatement(sql);
		        prep.setString(1, name);
		        
		        ResultSet rs = prep.executeQuery();
		        
		        while (rs.next()) {
		            float liters = rs.getFloat("liters");
		            Date date = rs.getDate("date");
		            int hospitalId = rs.getInt("hospital_id");
		            int bloodId = rs.getInt("blood_id");
		            
		            Hospital hospitalRequesting= searchHospitalById(hospitalId);
		            Blood bloodRequesting=bloodManager.searchBloodById(bloodId);
		            
		            Request request = new Request(liters, date, hospitalRequesting,bloodRequesting);
		            requests.add(request);
		        }
		        
		        rs.close();
		        prep.close();
		    } catch (SQLException e) {
		        System.err.println("Error listing requests: " + e.getMessage());
		    }
		    
		
		return requests;
		
	}
	public Hospital searchHospitalById(Integer id) {
		Hospital hospital = null;

	    try {
	        String sql = "SELECT id, name, address, email FROM hospital WHERE id = ?";
	        PreparedStatement prep = manager.getConnection().prepareStatement(sql);
	        prep.setInt(1, id);

	        ResultSet rs = prep.executeQuery();

	        if (rs.next()) {
	            String name = rs.getString("name");
	            String address = rs.getString("address");
	            String email = rs.getString("email");

	            hospital = new Hospital(id, name, address, email);
	        }

	        rs.close();
	        prep.close();
	    } catch (SQLException e) {
	        System.err.println("Error searching hospital by ID: " + e.getMessage());
	    }
	    return hospital;
	}
	


	

}
