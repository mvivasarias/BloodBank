package ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import bloodBankJDBC.JDBCBloodManager;
import bloodBankJDBC.JDBCContractManager;
import bloodBankJDBC.JDBCDonationManager;
import bloodBankJDBC.JDBCDonorManager;
import bloodBankJDBC.JDBCPersonalManager;
import bloodBankJDBC.JDBCStockManager;
import bloodBankPOJOs.Contract;
import bloodBankPOJOs.Donation;
import bloodBankPOJOs.Personal;
import utilities.Utilities;

public class PersonalMenu {

	private String email;

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public PersonalMenu(String email) {
		super();
		this.email = email;

	}

	public void personalMenuOptions(String email, JDBCBloodManager bloodManager, JDBCContractManager contractManager,
			JDBCDonationManager donationManager, JDBCDonorManager donorManager, JDBCPersonalManager personalManager,
			JDBCStockManager stockManager) {

		try {
			int choice;
			do {
				System.out.println("Choose an option");
				System.out.println("1. Register a nurse");
				System.out.println("2. Modify information of nurse");
				System.out.println("3. Delete nurse");
				System.out.println("4. Register a donor ");
				System.out.println("5. Modify information of a donor.");
				System.out.println("6. Delete donor");
				System.out.println("7. List donors");
				System.out.println("8. Add donation");
				System.out.println("9. Delete donation");
				System.out.println("10. List donations");
				System.out.println("11. Get list of blood extractions ordered by id");
				System.out.println("12. Get stock ordered by blood type");

				System.out.println("0. Return.");

				choice = Integer.parseInt(reader.readLine());

				switch (choice) {
				case 1:
					registerNurse(personalManager, contractManager);
					break;
				case 2:
					modifyNurse(personalManager);
					break;
				case 3:
					deleteNurse(personalManager);
					break;
				case 4:
					// registerDonor();
					break;
				case 5:
					// deleteDonor();
					break;
				case 6:
					// listDonors();
					break;
				case 7:
					// addDonation();
					break;
				case 8:
					// deleteDonation();
					break;
				case 9:
					// listDonations();
					break;
				case 10:
					// listOfBloodExtractions();
					break;
				case 11:
					// stockOrderedByBloodType();
					break;
				case 0:
					System.out.println("Back to main menu");

				}

			} while (choice != 0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void registerNurse(JDBCPersonalManager personalManager, JDBCContractManager contractManager) {
		System.out.println("Type your name");
		String name = Utilities.readString();
		System.out.println("Type your surname ");
		String surname = Utilities.readString();
		System.out.println("Type yor email");
		String email = Utilities.readString();
		System.out.println("Establishing a contract");
		Contract contract = makeAContract();
		System.out.println("Please introduce a photo");
		byte[] photo = null; // TODO como guardar la foto
		contractManager.addContract(contract); // SQL DONE

		Personal nurse = new Personal(name, surname, email, contract, photo);
		personalManager.addPersonal(nurse);// SQL DONE
		System.out.println("Please introduce a photo");

	}

	private void modifyNurse(JDBCPersonalManager personalManager) {
		System.out.println("Type the email of the nurse you want to modify information from ");
		String email = Utilities.readString();
		Personal nurseModifying = personalManager.searchPersonalByEmail(email); // sql done

		if (nurseModifying != null) {

			System.out.println("Enter new name :");
			String newName = Utilities.readString();

			System.out.println("Enter new surname :");
			String newSurname = Utilities.readString();

			System.out.println("Enter new email :");
			String newEmail = Utilities.readString();

			System.out.println("Enter new photo :");
			String newPhoto = Utilities.readString();
			personalManager.modifyPersonal(nurseModifying, newName, newSurname, newEmail, newPhoto);

		} else {
			System.out.println("Nurse with email " + email + " not found.");
		}
	}

	private void deleteNurse(JDBCPersonalManager personalManager) {
		Integer id_nurse_deleted = Utilities.readInteger("Introduce the id of the nurse you want to delete");
		personalManager.deletePersonalByID(id_nurse_deleted); // SQL DONE

	}

	private static Contract makeAContract() {
		Integer hoursToWork = Utilities.askHoursWanted("How many hours do you want to work?");
		Integer salaryDependingOnHoursWorked = Utilities.salaryCalculator(hoursToWork);

		return new Contract(hoursToWork, salaryDependingOnHoursWorked);

	}
}
