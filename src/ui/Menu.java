package ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;

import bloodBankPOJOs.Hospital;
import bloodBankPOJOs.Role;
import bloodBankPOJOs.User;
import bloodBankIfaces.UserManager;
import bloodBankJDBC.JDBCBloodManager;
import bloodBankJDBC.JDBCContractManager;
import bloodBankJDBC.JDBCDonationManager;
import bloodBankJDBC.JDBCDonorManager;
import bloodBankJDBC.JDBCHospitalManager;
import bloodBankJDBC.JDBCManager;
import bloodBankJDBC.JDBCPersonalManager;
import bloodbankJPA.JPAuserManager;

public class Menu {

	private static JDBCManager jdbcManager;
	private static JDBCBloodManager bloodManager;
	private static JDBCContractManager contractManager;
	private static JDBCDonationManager donationManager;
	private static JDBCDonorManager donorManager;
	private static JDBCHospitalManager hospitalManager;
	private static JDBCPersonalManager personalManager;

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static UserManager usermanager;

	public static void main(String[] args) {

		jdbcManager = new JDBCManager();
		bloodManager = new JDBCBloodManager(jdbcManager);
		contractManager = new JDBCContractManager(jdbcManager);
		donationManager = new JDBCDonationManager(jdbcManager);
		donationManager = new JDBCDonationManager(jdbcManager,donorManager,personalManager);
		donorManager = new JDBCDonorManager(jdbcManager);
		hospitalManager = new JDBCHospitalManager(jdbcManager);
		hospitalManager = new JDBCHospitalManager(jdbcManager,bloodManager);
		personalManager = new JDBCPersonalManager(jdbcManager);
		personalManager = new JDBCPersonalManager(jdbcManager, contractManager);
		

		usermanager = new JPAuserManager();
		usermanager.connect();

		try {
			int choice;

			do {

				System.out.println("Choose an option");
				System.out.println("1. Login User");
				System.out.println("2. Sign-up new user");
				System.out.println("0. Exit.");

				choice = Integer.parseInt(reader.readLine());

				switch (choice) {
				case 1:
					login();
					break;
				case 2:
					System.out.println("Add info of new user.");
					signUpUser();
					break;

				case 3:
					System.out.println("Udpate the password of an exissting user.");
					updatePassword();
					break;
				case 0:
					System.out.println("Exiting application.");
					jdbcManager.disconnect();
				}

			} while (choice != 0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private static void login() throws Exception {
		
		System.out.println("Email: ");
		String email = reader.readLine();

		System.out.println("Password: ");
		String password = reader.readLine();

		User u = usermanager.checkPassword(email, password);
		
		if(u != null) { 

		if (u.getRole().getName().equals("personal")) {
			System.out.println("Login of personal successful!");
			PersonalMenu personalMenu = new PersonalMenu(email);
			personalMenu.personalMenuOptions(email,bloodManager,contractManager,donationManager,donorManager,personalManager);

		} else {
			System.out.println("Login of hospital successful!");
			HospitalMenu hospitalMenu = new HospitalMenu(email);
		hospitalMenu.hospitalMenuOptions(email,hospitalManager,bloodManager);
		}} else {
			System.out.println("You are not signed up yet, please go to option 2 first!");
		}
	}

	private static void signUpUser() {
		
		try {
			System.out.println("Introduce email: ");
			String email = reader.readLine();
			System.out.println("Introduce the password");
			String password = reader.readLine();

			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] pass = md.digest();

			System.out.println("Introduce the role of the user.\n 1: personal, 2: hospital");
			Integer rol = Integer.parseInt(reader.readLine());
			Role r = usermanager.getRole(rol);

			User u = new User(email, pass, r);
			usermanager.newUser(u);
			
				if (u != null & u.getRole().getName().equals("hospital")) {

					System.out.println("Type the name of the Hospital");
					String name = Utilities.readString();
					System.out.println("Type the address of the Hospital");
					String address = Utilities.readString();
					String hospitalEmailUser = email;

					Hospital hospitalToAdd = new Hospital(name, address, hospitalEmailUser);
					hospitalManager.addHospital(hospitalToAdd);		
				}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void updatePassword() throws Exception {

		System.out.println("Email: ");
		String email = reader.readLine();

		System.out.println("Enter current Password");
		String passwd = reader.readLine();

		System.out.println("Enter new Password");
		String new_passwd = reader.readLine();

		User u = usermanager.checkPassword(email, passwd);

		if (u != null) {
			System.out.println("Login of user successful!");
			usermanager.changePassword(u, new_passwd);
		}

	}

}
