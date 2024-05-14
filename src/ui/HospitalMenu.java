package ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import utilities.Utilities;
import bloodBankJDBC.JDBCBloodManager;
import bloodBankJDBC.JDBCHospitalManager;
import bloodBankJDBC.JDBCStockManager;
import bloodBankPOJOs.Blood;
import bloodBankPOJOs.Hospital;
import bloodBankPOJOs.Request;

public class HospitalMenu {

	private String email;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public HospitalMenu(String email) {
		super();
		this.email = email;
	}

	public void hospitalMenuOptions(String email, JDBCHospitalManager hospitalManager, JDBCBloodManager bloodManager,
			JDBCStockManager stockManager) {
		try {
			int choice;
			do {
				System.out.println("Choose an option");
				System.out.println("1. Request blood");
				System.out.println("2. Add hospital");
				System.out.println("3. Delete hospital");
				System.out.println("4. Modify hospital");
				System.out.println("5. Show requests of a hospital");
				System.out.println("0. Return.");

				choice = Integer.parseInt(reader.readLine());

				switch (choice) {
				case 1:
					requestBlood(hospitalManager, bloodManager, stockManager);
					break;
				case 2:
					addHospital(hospitalManager);
					break;
				case 3:
					deleteHospital(hospitalManager);
					break;
				case 4:
					// modifyHospital(hospitalManager);
					break;
				case 5:
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

	private void requestBlood(JDBCHospitalManager hospitalManager, JDBCBloodManager bloodManager,
			JDBCStockManager stockManager) throws Exception {

		Integer id = Utilities.readInteger("Type the id of the Hospital making the request");
		Hospital hospitalRequesting = hospitalManager.searchHospitalById(id);

		if (hospitalRequesting != null) {

			System.out.println("Type the liters needed");
			float liters = Utilities.readfloat();
			System.out.println("Type the date of the request yyyy/mm/dd");
			String dateOfRequest = reader.readLine();
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			Date date = (Date) df.parse(dateOfRequest);
			System.out.println("Type the blood type needed");
			String bloodType = Utilities.askBloodType("Introduce blood type:");

			List<Blood> bloodRecords = bloodManager.searchBloodType(bloodType);
			if (bloodRecords.isEmpty()) {
				System.out.println("Blood type " + bloodType + " is not available.");
			} else {

				float totalLitersAvailable = stockManager.getTotalLitersAvailable(bloodType);

				if (totalLitersAvailable >= liters) {

					System.out.println("Sufficient blood of type " + bloodType + " is available for the request.");
					bloodManager.updateBloodLiters(bloodType, liters);

				} else {
					System.out.println("Insufficient blood of type " + bloodType + " available. Available liters: "
							+ totalLitersAvailable);
				}
			}
		} else {
			System.out.println("Hospital not found with such ID");

		}
	}

	private void printRequestsFromHospital(JDBCHospitalManager hospitalManager) {
		List<Request> requests = null;
		System.out.println("Type the name of the Hospital");
		String name = Utilities.readString();
		requests = hospitalManager.getRequestsOfHospital(name);
		System.out.println(requests);

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
