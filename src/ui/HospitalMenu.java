package ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import bloodBankJDBC.JDBCBloodManager;
import bloodBankJDBC.JDBCHospitalManager;
import bloodBankPOJOs.Blood;
import bloodBankPOJOs.Hospital;
import bloodBankPOJOs.Request;

public class HospitalMenu {

	private String email;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private Hospital hospital;

	public HospitalMenu(String email) {
		super();
		this.email = email;
	}

	public void hospitalMenuOptions(String email, JDBCHospitalManager hospitalManager, JDBCBloodManager bloodManager) {

		hospital = hospitalManager.searchHospitalByEmail(email);
		try {
			int choice;
			do {
				System.out.println("\nWelcome to the hospital menu of the blood bank database\n" + "Select an option");
				System.out.println("1. Request blood");
				System.out.println("2. Show requests of this hospital");
				System.out.println("0. Return.");

				choice = Integer.parseInt(reader.readLine());

				switch (choice) {
				case 1:
					requestBlood(hospitalManager, bloodManager);
					break;

				case 2:
					printRequestsFromHospital(hospitalManager);
					break;
				case 0:
					System.out.println("Back to main menu");

				}

			} while (choice != 0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void requestBlood(JDBCHospitalManager hospitalManager, JDBCBloodManager bloodManager) throws Exception {

		if (hospital != null) {

			System.out.println("Type the liters needed");
			float litersNeeded = Utilities.readfloat();
			 
            LocalDateTime currentDateTime = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(currentDateTime);

            
            Date dateRequestSQL = new Date(timestamp.getTime());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            
            System.out.println("Request date and time: " + currentDateTime.format(formatter));

			System.out.println("\nType the blood type needed");
			String bloodType = Utilities.askBloodType("Introduce blood type:");
			
			List<Blood> bloodRecords = bloodManager.searchBloodType(bloodType);
			
			if(!bloodRecords.isEmpty()) {
			System.out.println("Blood stock of "+ bloodType+ " in the blood bank");
			for (Blood blood: bloodRecords) {
				
				System.out.println(blood.toString());
			} }
			System.out.println("\n");
			if (bloodRecords.isEmpty()) {
				System.out.println("Blood type " + bloodType + " is not available.");
			} else {

				float totalLitersAvailable = bloodManager.getTotalLitersAvailable(bloodType);

				if (totalLitersAvailable >= litersNeeded) {

					System.out.println("Sufficient blood of type " + bloodType + " is available for the request.");

					float litersRemaining = litersNeeded;
					for (Blood blood : bloodRecords) {

						if (litersRemaining <= 0) {
							break;
						}
						float currentLiters = blood.getLiters();
						Integer blood_id = blood.getId();

						if (currentLiters <= litersRemaining) {

							hospitalManager.addRequest(hospital.getId(), blood_id, currentLiters, dateRequestSQL);
							bloodManager.deleteBloodById(blood_id);
							litersRemaining = litersRemaining - currentLiters;

						} else {
							bloodManager.updateStockLitersById(blood_id, currentLiters - litersRemaining);
							hospitalManager.addRequest(hospital.getId(), blood_id, litersRemaining, dateRequestSQL);
							litersRemaining = 0;
						}
					}
					System.out.println("Blood request has been successfully processed.\n");
					
					List<Blood> bloodLeft = bloodManager.searchBloodType(bloodType);
					for (Blood blood: bloodLeft) {
						System.out.println("Blood stock of "+ bloodType+ " in the blood bank after request.");
						System.out.println(blood.toString());
					}

				} else {
					System.out.println("Insufficient blood of type " + bloodType + " available. Available liters: "
							+ totalLitersAvailable);
				}

			}
		}

	}

	private void printRequestsFromHospital(JDBCHospitalManager hospitalManager) {
		List<Request> requests = hospitalManager.getRequestsOfHospitalByName(hospital.getName());
		if (requests.isEmpty()) {
			System.out.println("No requests found for hospital: " + hospital.getName());
		} else {
			for (Request request : requests) {
				System.out.println(request);
			}
		}
	}

	private void addHospital(JDBCHospitalManager hospitalManager) {
		System.out.println("Type the name of the Hospital");
		String name = Utilities.readString();
		System.out.println("Type the address of the Hospital");
		String address = Utilities.readString();
		String hospitalEmailUser = this.email;

		Hospital hospitalToAdd = new Hospital(name, address, hospitalEmailUser);
		hospitalManager.addHospital(hospitalToAdd);

	}

	private void deleteHospital(JDBCHospitalManager hospitalManager) {
		System.out.println("Type the name Hospital you want to delete");
		String name = Utilities.readString();
		hospitalManager.deleteHospital(name);

	}

}
