package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import bloodBankJDBC.JDBCBloodManager;
import bloodBankJDBC.JDBCContractManager;
import bloodBankJDBC.JDBCDonationManager;
import bloodBankJDBC.JDBCDonorManager;
import bloodBankJDBC.JDBCPersonalManager;
import bloodBankPOJOs.Blood;
import bloodBankPOJOs.Contract;
import bloodBankPOJOs.Donation;
import bloodBankPOJOs.Donor;
import bloodBankPOJOs.Personal;
import bloodBankPOJOs.Stock;

public class PersonalMenu {

	private String email;

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public PersonalMenu(String email) {
		super();
		this.email = email;

	}

	public void personalMenuOptions(String email, JDBCBloodManager bloodManager, JDBCContractManager contractManager,
			JDBCDonationManager donationManager, JDBCDonorManager donorManager, JDBCPersonalManager personalManager) {

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
				System.out.println("11. Get list of blood extractions");

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
					registerDonor(personalManager, donorManager);
					break;
				case 5:
					modifyDonor(donorManager);
					break;
				case 6:
					deleteDonor(donorManager);
					break;
				case 7:
					listDonors(donorManager);
					break;
				case 8:
					addDonation(personalManager, donorManager, donationManager, bloodManager);
					break;
				case 9:
					deleteDonation(donationManager);
					break;
				case 10:
					listDonations(donationManager);
					break;
				case 11:
					listOfBloodExtractions(bloodManager);
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

	private void registerDonor(JDBCPersonalManager personalManager, JDBCDonorManager donorManager)
			throws ParseException {
		if (personalManager.isPersonalTableNotEmpty()) { // if there is some nurse the registration can be done
			System.out.println("Donor´s name :");
			String name = Utilities.readString();

			System.out.println("Donor´s surname :");
			String surname = Utilities.readString();

			System.out.println("Donor´s dob in formal yyyy/mm/dd");
			String dob_str = Utilities.readString();
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			Date dob = (Date) df.parse(dob_str);

			String bloodType = Utilities.askBloodType("Donor´s blood type");

			Integer timesDonated = 0;
			Donor donorRegistered = new Donor(name, surname, dob, bloodType, timesDonated);

			donorManager.addDonor(donorRegistered);

		} else {
			System.out.println("No personal for registering a donor");

		}
	}

	private void modifyDonor(JDBCDonorManager donorManager) throws ParseException {

		Integer idDonor = Utilities.readInteger("Enter the donor ID you want to modify :");

		Donor donorExisting = donorManager.getDonorByID(idDonor);

		if (donorExisting != null) {

			System.out.println("Donor´s name modification:");
			String name = Utilities.readString();

			System.out.println("Donor´s surname modification :");
			String surname = Utilities.readString();

			System.out.println("Donor´s dob in formal yyyy/mm/dd modification ");
			String dob_str = Utilities.readString();
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			Date dob = (Date) df.parse(dob_str);

			String bloodType = Utilities.askBloodType("Donor´s blood type modification");
			int times = donorExisting.getTimes(); // not modified

			Donor donorModified = new Donor(idDonor, name, surname, dob, bloodType, times);
			donorManager.modifyDonor(donorModified);

			System.out.println("Donor modified successfully.");

		} else {
			System.out.println("No donor existing with that id ");
		}

	}

	private void deleteDonor(JDBCDonorManager donorManager) {
		Integer idDonor = Utilities.readInteger("Enter the donor ID you want to delete :");

		Donor donorExisting = donorManager.getDonorByID(idDonor);

		if (donorExisting != null) {
			donorManager.deleteDonor(donorExisting.getId());
		} else {
			System.out.println("No donor existing with that id ");
		}

	}

	private void listDonors(JDBCDonorManager donorManager) {
		System.out.println("List of donors ordered by name:");
		List<Donor> donors = donorManager.getDonorsByName();
		for (Donor donor : donors) {
			System.out.println(donor);
		}

	}

	private void addDonation(JDBCPersonalManager personalManager, JDBCDonorManager donorManager,
			JDBCDonationManager donationManager, JDBCBloodManager bloodManager) {
		System.out.println("Type the email of the nurse attending ");
		String email = Utilities.readString();

		Personal nurseAttending = personalManager.searchPersonalByEmail(email);
		System.out.println("Type the name of the donor ");
		String nameDonor = Utilities.readString();
		System.out.println("Type the surname of the donor ");
		String surname = Utilities.readString();
		Donor donorDonating = donorManager.searchDonorByNameAndSurname(nameDonor, surname);

		System.out.println("How many liters is the donor donating ");
		float amountDonating = Utilities.readfloat();

		LocalDate localDate = LocalDate.now();
		Date currentDate = Date.valueOf(localDate);

		Donation newDonation = new Donation(currentDate, amountDonating, donorDonating, nurseAttending);
		donationManager.addDonation(newDonation);
		donorManager.incrementDonorTimes(donorDonating);

		Blood newBlood = new Blood(donorDonating.getBloodtype(), amountDonating, currentDate);

		bloodManager.addBlood(newBlood);

		donationManager.addDonationBlood(newDonation.getId(), newBlood.getId());

	}

	private void deleteDonation(JDBCDonationManager donationManager) throws IOException {
		int choice;
		System.out.println("How would you like to delete a donation:");
		System.out.println("1. Delete donation by ID");
		System.out.println("2. Delete donation by blood type");
		System.out.println("0. Exit");

		choice = Utilities.readInteger("Enter an option");

		switch (choice) {
		case 1:
			int donationId = Utilities.readInteger("Enter the ID of the donation to delete");
			donationManager.deleteDonationById(donationId);
			break;
		case 2:
			String bloodType = Utilities.askBloodType("Select the blood type you want to delete");
			donationManager.deleteDonationByBloodType(bloodType);
			break;
		case 0:
			System.out.println("Exiting option of deleting donation.");
			break;
		default:
			System.out.println("Invalid choice. Please enter a valid option.");
			break;
		}

	}

	private void listDonations(JDBCDonationManager donationManager) {
		System.out.println("List of donations ordered by date:");
		List<Donation> donations = donationManager.getDonationsByDate();

		for (Donation donation : donations) {
			System.out.println(donation);
		}

	}

	private void listOfBloodExtractions(JDBCBloodManager bloodManager) {
		System.out.println("List of blood extractions ordered by blood type:");
		List<Blood> bloods = bloodManager.getBloodListByType();
		for (Blood blood : bloods) {
			System.out.println(blood);
		}

	}
}
