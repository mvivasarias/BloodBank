package ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import utilities.Utilities;
import bloodBankJDBC.JDBCBloodManager;
import bloodBankJDBC.JDBCHospitalManager;
import bloodBankPOJOs.Blood;
import bloodBankPOJOs.Hospital;
import bloodBankPOJOs.Request;

public class HospitalMenu {

	private String email;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public HospitalMenu(String email) {
		super();
		this.email= email;
	}

	public void hospitalMenuOptions(String email,JDBCHospitalManager hospitalManager,JDBCBloodManager bloodManager ) {
		try {
			int choice;
			do {
				System.out.println("Choose an option");
				System.out.println("1. Request blood");
				System.out.println("2. Delete hospital");
				System.out.println("0. Return.");

				choice = Integer.parseInt(reader.readLine());

				switch (choice) {
				case 1:
					requestBlood( hospitalManager, bloodManager);
					break;
				case 2:
					deleteHospital(hospitalManager);
					break;
				case 0:
					System.out.println("Back to main menu");

				}

			} while (choice != 0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void requestBlood(JDBCHospitalManager hospitalManager,JDBCBloodManager bloodManager) throws Exception { // hospital created and request done ADD a hospital and a REQUEST to
													// table
													// delete that blood and also from the stock (cascade)

		System.out.println("Type the name of the Hospital");
		String name = Utilities.readString();
		System.out.println("Type the address of the Hospital");
		String address = Utilities.readString();
		System.out.println("Type the email of the hospital user");
		String hospitalEmailUser = Utilities.readString();

		Hospital hospitalRequestingBlood = new Hospital(name, address, hospitalEmailUser);

		System.out.println("Type the liters needed");
		float liters = Utilities.readfloat();
		System.out.println("Type the date of the request yyyy/mm/dd");
		String dateOfRequest = reader.readLine();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date date = (Date) df.parse(dateOfRequest);
		System.out.println("Type the blood type needed");
		String bloodType = Utilities.askBloodType("Introduce blood type:");

		Request request = new Request(liters, date, bloodType);

		Blood bloodRequested = new Blood(bloodType);

		hospitalManager.addHospital(hospitalRequestingBlood);
		bloodManager.deleteBlood(bloodRequested); // stock also has to be deleted

	}
	/*
	 * private void printRequestsFromHospital(){ List <Request> requests=null;
	 * System.out.println("Type the name of the Hospital"); String name =
	 * Utilities.readString(); requests=
	 * hospitalManager.getRequestsOfHospital(name); System.out.println(requests);
	 * 
	 * }
	 */

	private void deleteHospital(JDBCHospitalManager hospitalManager) {
		System.out.println("Type the name of the Hospital you want to delete");
		String name = Utilities.readString();
		hospitalManager.deleteHospital(name);

	}

}
