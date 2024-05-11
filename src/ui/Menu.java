package ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;

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
import bloodBankJDBC.JDBCStockManager;
import bloodbankJPA.JPAuserManager;

public class Menu {

	private static JDBCManager jdbcManager;
	private static JDBCBloodManager bloodManager;
	private static JDBCContractManager contractManager;
	private static JDBCDonationManager donationManager;
	private static JDBCDonorManager donorManager;
	private static JDBCHospitalManager hospitalManager;
	private static JDBCPersonalManager personalManager;
	private static JDBCStockManager stockManager;

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static UserManager usermanager;

	public static void main(String[] args) {

		jdbcManager = new JDBCManager();
		bloodManager = new JDBCBloodManager(jdbcManager);
		contractManager = new JDBCContractManager(jdbcManager);
		donationManager = new JDBCDonationManager(jdbcManager);
		donorManager = new JDBCDonorManager(jdbcManager);
		hospitalManager = new JDBCHospitalManager(jdbcManager);
		personalManager = new JDBCPersonalManager(jdbcManager);
		stockManager = new JDBCStockManager(jdbcManager);

		usermanager = new JPAuserManager();

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
				case 2:
					System.out.println("Add info of new user.");
					signUpUser();

				case 3:
					System.out.println("Udpate the password of an exissting user.");
					updatePassword();
				case 0:
					System.out.println("Exiting application.");
					jdbcManager.disconnect();
				}

			} while (choice != 0);

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

	private static void login() throws Exception {
		
		System.out.println("Email: ");
		String email = reader.readLine();

		System.out.println("Password: ");
		String password = reader.readLine();

		User u = usermanager.checkPassword(email, password);

		if (u != null & u.getRole().getName().equals("personal")) {
			System.out.println("Login of personal successful!");
			PersonalMenu personalMenu = new PersonalMenu(email);
			personalMenu.personalMenuOptions(email,bloodManager,contractManager,donationManager,donorManager,personalManager,stockManager);

		} else {
			System.out.println("Login of hospital successful!");
			HospitalMenu hospitalMenu = new HospitalMenu(email);
			hospitalMenu.hospitalMenuOptions(email,hospitalManager,bloodManager);
		}
	}

	private static void signUpUser() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Introduce email: ");
			String email = reader.readLine();
			System.out.println("Introduce the password");
			String password = reader.readLine();

			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] pass = md.digest();

			System.out.println("Introduce the role of the user. 1: personal, 2: hospital");
			Integer rol = Integer.parseInt(reader.readLine());
			Role r = usermanager.getRole(rol);

			User u = new User(email, pass, r);

			usermanager.newUser(u);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
