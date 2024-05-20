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
				System.out.println("\nWelcome to the personal menu of the blood bank database\n"+"Select an option");
				System.out.println("1. Register in the  blood bank database");
				System.out.println("2. Modify your information ");
				System.out.println("3. Delete personal");
				System.out.println("4. List personal");
				System.out.println("5. Register a donor ");
				System.out.println("6. Modify information of a donor");
				System.out.println("7. Delete donor");
				System.out.println("8. List donors");
				System.out.println("9. Add donation");
				System.out.println("10. Delete donation");
				System.out.println("11. List donations");
				System.out.println("12. Get list of blood extractions");

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
					listPersonal(personalManager);
					break;
				case 5:
					registerDonor(personalManager, donorManager);
					break;
				case 6:
					modifyDonor(personalManager, donorManager);
					break;
				case 7:
					deleteDonor(personalManager, donorManager);
					break;
				case 8:
					listDonors(donorManager);
					break;
				case 9:
					addDonation(personalManager, donorManager, donationManager, bloodManager);
					break;
				case 10:
					deleteDonation(personalManager, donationManager);
					break;
				case 11:
					listDonations(donationManager);
					break;
				case 12:
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

		System.out.println("Establishing a contract");
		Contract contract = Utilities.makeAContract();
		Contract contractAdded=contractManager.addContract(contract); // SQL DONE

		System.out.println("Please introduce a photo");
		byte[] photo = null; // TODO como guardar la foto
		
		Personal nurse = new Personal(name, surname, this.email, contractAdded, photo);
		
		personalManager.addPersonal(nurse);// SQL DONE

	}

	private void modifyNurse(JDBCPersonalManager personalManager) {

		Personal nurseModifying = personalManager.searchPersonalByEmail(this.email); // sql done

		if (nurseModifying != null) {

			System.out.println("Moidfy your name :");
			String newName = Utilities.readString();

			System.out.println("Modify your surname :");
			String newSurname = Utilities.readString();

			System.out.println("Modify your email :");
			String newEmail = Utilities.readString();
			this.email = newEmail;

			System.out.println("Enter new photo :");
			String newPhoto = Utilities.readString();
			
			System.out.println("proceeding to modify :");
			personalManager.modifyPersonal(nurseModifying, newName, newSurname, this.email, newPhoto);

		} else {
			System.out.println("You have entered the personal menu with the email:  " + this.email
					+ " but you are not registered in the blood bank database, please register first!");
		}
	}

	private void deleteNurse(JDBCPersonalManager personalManager) {

		Personal nurseDeleteing = personalManager.searchPersonalByEmail(this.email); // sql done

		if (nurseDeleteing != null) {
			System.out.println("Introduce the id of the nurse you want to delete");
			Integer id_nurse_deleted = Utilities.readInteger("Introduce the id of the nurse you want to delete");
			personalManager.deletePersonalByID(id_nurse_deleted);// SQL DONE
		} else {
			System.out.println(
					"You are not registered in the blood bank database, please register first to delete other people");
		}
	}

	private void listPersonal(JDBCPersonalManager personalManager) {
		System.out.println("List of nurses registered ordered by id:");
		List<Personal> nurses = personalManager.listPersonal();
		for (Personal nurse : nurses) {
			System.out.println(nurse);
		}

	}

	private void registerDonor(JDBCPersonalManager personalManager, JDBCDonorManager donorManager)
			throws ParseException {
		if (personalManager.isPersonalTableNotEmpty()) { // if there is some nurse the registration can be done

			System.out.println("Donor´s name :");
			String name = Utilities.readString();

			System.out.println("Donor´s surname :");
			String surname = Utilities.readString();

			System.out.println("Donor´s dob in format yyyy/MM/dd");
			String dob_str = Utilities.readString();
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			java.sql.Date dobSql = java.sql.Date.valueOf(dob_str.replace("/", "-"));
			//Date utilDate = (Date) df.parse(dob_str);
			//java.sql.Date dobSql = new java.sql.Date(utilDate.getTime());
	        
	        String bloodType = Utilities.askBloodType("Donor´s blood type");

			Integer timesDonated = 0;
			Donor donorRegistered = new Donor(name, surname, dobSql, bloodType, timesDonated);

			donorManager.addDonor(donorRegistered);

		} else {
			System.out.println("No personal for registering a donor");

		}
	}

	private void modifyDonor(JDBCPersonalManager personalManager, JDBCDonorManager donorManager) throws ParseException {

		if (personalManager.isPersonalTableNotEmpty()) {
			Integer idDonor = Utilities.readInteger("Enter the donor ID you want to modify :");

			Donor donorExisting = donorManager.getDonorByID(idDonor);

			if (donorExisting != null) {

				System.out.println("Donor´s name modification:");
				String name = Utilities.readString();

				System.out.println("Donor´s surname modification :");
				String surname = Utilities.readString();

				System.out.println("Donor´s dob in format yyyy/mm/dd modification ");
				String dob_str = Utilities.readString();
				java.sql.Date dobSql = java.sql.Date.valueOf(dob_str.replace("/", "-"));
				//DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
				//Date dob = (Date) df.parse(dob_str);

				String bloodType = Utilities.askBloodType("Donor´s blood type modification");

				int times = donorExisting.getTimes(); // not modified

				Donor donorModified = new Donor(idDonor, name, surname, dobSql, bloodType, times);
				donorManager.modifyDonor(donorModified);

				System.out.println("Donor modified successfully.");

			} else {
				System.out.println("No donor existing with that id ");
			}
		} else {
			System.out.println("No personal for modifying a donor, please register in the blood bank database first");
		}

	}

	private void deleteDonor(JDBCPersonalManager personalManager, JDBCDonorManager donorManager) {
		Personal loggedInPersonal = personalManager.searchPersonalByEmail(this.email);

		if (loggedInPersonal != null) {

			Integer idDonor = Utilities.readInteger("Enter the donor ID you want to delete :");
			Donor donorExisting = donorManager.getDonorByID(idDonor);

			if (donorExisting != null) {
				donorManager.deleteDonor(donorExisting.getId());
			} else {
				System.out.println("No donor existing with that id ");
			}
		} else {
			System.out.println(
					"You are not registered in the blood bank database, please register first to delete a donor");
		}

	}

	private void listDonors(JDBCDonorManager donorManager) {
		System.out.println("List of donors ordered by name:");
		List<Donor> donors = donorManager.listDonorsByName();
		for (Donor donor : donors) {
			System.out.println(donor);
		}

	}

	private void addDonation(JDBCPersonalManager personalManager, JDBCDonorManager donorManager,
			JDBCDonationManager donationManager, JDBCBloodManager bloodManager) {

		System.out.println("Type the email of the nurse who has performed the extraction of blood ");
		String email = Utilities.readString();

		Personal nurseAttending = personalManager.searchPersonalByEmail(email);

		System.out.println("Type the name of the donor ");
		String nameDonor = Utilities.readString();
		System.out.println("Type the surname of the donor ");
		String surname = Utilities.readString();

		System.out.println("Type the blood type of the donor ");
		String bloodType = Utilities.askBloodType("Blood type");

		Donor donorDonating = donorManager.searchDonorByNameSurnameBloodtype(nameDonor, surname, bloodType);

		System.out.println("How many liters is the donor donating ");
		float amountDonating = Utilities.readfloat();

		LocalDate localDate = LocalDate.now();
		Date currentDate = Date.valueOf(localDate);

		Donation newDonation = new Donation(currentDate, amountDonating, donorDonating, nurseAttending);
		Donation donationAdded=donationManager.addDonation(newDonation);
		System.out.println("donation id"+donationAdded.getId());
		donorManager.incrementDonorTimes(donorDonating); // the donor´s times donating increment 1

		Blood newBlood = new Blood(donorDonating.getBloodtype(), amountDonating, currentDate);

		Blood bloodAdded=bloodManager.addBlood(newBlood);
		System.out.println("blood"+bloodAdded.getId());

		donationManager.addDonationBlood(donationAdded.getId(), bloodAdded.getId());

	}

	private void deleteDonation(JDBCPersonalManager personalManager, JDBCDonationManager donationManager)
			throws IOException {

		Personal loggedInPersonal = personalManager.searchPersonalByEmail(this.email);

		if (loggedInPersonal != null) {
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
		} else {
			System.out.println(
					"You are not registered in the blood bank database, please register first to delete a donation");
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
