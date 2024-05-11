package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import utilities.Utilities;
import bloodBankJDBC.JDBCBloodManager;
import bloodBankJDBC.JDBCContractManager;
import bloodBankJDBC.JDBCDonationManager;
import bloodBankJDBC.JDBCDonorManager;
import bloodBankJDBC.JDBCHospitalManager;
import bloodBankJDBC.JDBCManager;
import bloodBankJDBC.JDBCPersonalManager;
import bloodBankJDBC.JDBCStockManager;
import bloodBankPOJOs.Blood;
import bloodBankPOJOs.Donation;
import bloodBankPOJOs.Hospital;
import bloodBankPOJOs.Request;
import bloodBankPOJOs.Stock;

public class HospitalMenu {

	private String email;
	private String password;
	private static JDBCManager jdbcManager;
	private static JDBCBloodManager bloodManager;
	private static JDBCContractManager contractManager;
	private static JDBCDonationManager donationManager;
	private static JDBCDonorManager donorManager;
	private static JDBCHospitalManager hospitalManager;
	private static JDBCPersonalManager personalManager;
	private static JDBCStockManager stockManager;

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public HospitalMenu(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public void hospitalMenuOptions(String email, String password) {
		try {
			int choice;
			do {
				System.out.println("Choose an option");
				System.out.println("1. Request blood");
				System.out.println("2. Print requests from a Hospital");
				System.out.println("3. Delete hospital");
				System.out.println("0. Return.");

				choice = Integer.parseInt(reader.readLine());

				switch (choice) {
				case 1:
					requestBlood();
					break;
				case 2:
					printRequestsFromHospital();
				case 3:
					deleteHospital();
				case 0:
					System.out.println("Back to main menu");

				}

			} while (choice != 0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void requestBlood() throws Exception { // hospital created and request done ADD a hospital and a REQUEST to
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

		Blood bloodRequested = new Blood(bloodType, donation, hospitalRequestingBlood);
		private Integer id;
		private String bloodType;
		private Stock stock;
		private List<Donation> donations;
		private List<Hospital> hospitals;
		

		Request request = new Request(liters, date, bloodType);
		hospitalManager.addHospital(hospitalRequestingBlood);
		hospitalManager.requestForBlood(request);
		bloodManager.deleteBlood(bloodRequested); // stock also has to be deleted

	}
	private void printRequestsFromHospital(){
		List <Request> requests=null;
		System.out.println("Type the name of the Hospital");
		String name = Utilities.readString();
		requests= hospitalManager.getRequestsOfHospital(name);
		System.out.println(requests);
		
	}

	
	private void deleteHospital() {
		System.out.println("Type the name of the Hospital you want to delete");
		String name = Utilities.readString();
		hospitalManager.deleteHospital(name);
		
		
	}

	

}
