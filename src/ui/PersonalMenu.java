package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import bloodBankIfaces.XMLManager;
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
			JDBCDonationManager donationManager, JDBCDonorManager donorManager, JDBCPersonalManager personalManager,
			XMLManager xmlManager) {

		try {
			int choice;
			do {
				System.out.println("\nHELLO user!\nWelcome to the personal menu of the blood bank database\n"
						+ "Select an option (REGISTER FIRST!)\n");
				System.out.println("1. REGISTER as personal in the  blood bank database to perform any action");
				System.out.println("2. Personal options ");
				System.out.println("3. Donor options");
				System.out.println("4. Donations options");
				System.out.println("5. Get list of blood remaining");
				System.out.println("0. Return.");

				choice = Integer.parseInt(reader.readLine());

				switch (choice) {
				case 1:
					registerNurse(personalManager, contractManager);
					break;
				case 2:
					personalOption( personalManager,contractManager, xmlManager);
					break;
				case 3:
					 DonorOption(personalManager, donorManager);
					break;
				case 4:
					 DonationOption(personalManager,donorManager,donationManager,bloodManager, contractManager,xmlManager);
					break;
				case 5:
					listOfBloodExtractions(personalManager, bloodManager);
					break;
				case 0:
					System.out.println("Back to main menu");

				}

			} while (choice != 0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void personalOption(JDBCPersonalManager personalManager, JDBCContractManager contractManager, XMLManager xmlManager) {
		try {
			int choice;
			do {
				System.out.println("1. Modify your information ");
				System.out.println("2. Delete personal");
				System.out.println("3. List personal");
				System.out.println("4. Save you as personal to file");
				System.out.println("5. Load you as personal from xml");
				System.out.println("0. Return to main Personal menu.");
				choice = Integer.parseInt(reader.readLine());

				switch (choice) {
				case 1:
					modifyNurse(personalManager);
					break;
				case 2:
					deleteNurse(personalManager);
					break;
				case 3:
					listPersonal(personalManager);
					break;
				case 4:
					printMe(personalManager, xmlManager);
					break;
				case 5:
					loadPersonal(personalManager, xmlManager);
					break;
				case 0:
					System.out.println("Back to main personal menu");

				}

			} while (choice != 0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void DonorOption(JDBCPersonalManager personalManager, JDBCDonorManager donorManager) {
		try {
			int choice;
			do {
				System.out.println("1. Register a donor ");
				System.out.println("2. Modify information of a donor");
				System.out.println("3. Delete donor");
				System.out.println("4. List donors");
				System.out.println("0. Return to main Personal menu.");
				choice = Integer.parseInt(reader.readLine());

				switch (choice) {
				case 1:
					registerDonor(personalManager, donorManager);
					break;
				case 2:
					modifyDonor(personalManager, donorManager);
					break;
				case 3:
					deleteDonor(personalManager, donorManager);
					break;
				case 4:
					listDonors(personalManager, donorManager);
					break;
				case 0:
					System.out.println("Back to main personal menu");

				}

			} while (choice != 0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void DonationOption(JDBCPersonalManager personalManager, JDBCDonorManager donorManager,
			JDBCDonationManager donationManager, JDBCBloodManager bloodManager, JDBCContractManager contractManager,
			XMLManager xmlManager) {
		try {
			int choice;
			do {
				System.out.println("1. Add donation");
				System.out.println("2. Delete donation");
				System.out.println("3. List donations");
				System.out.println("4. Save a list of donations to file");
				System.out.println("5. Load a donation from xml");
				System.out.println("0. Return to main Personal menu.");
				
				choice = Integer.parseInt(reader.readLine());

				switch (choice) {
				
				case 1:
					addDonation(personalManager, donorManager, donationManager, bloodManager, contractManager,
							xmlManager);
					break;
				case 2:
					deleteDonation(personalManager, donationManager);
					break;
				case 3:
					listDonations(personalManager, donationManager);
					break;
				case 4:
					printDonations(personalManager, xmlManager);
					break;
				case 5:
					loadDonations(personalManager, xmlManager);
					break;
				case 0:
					System.out.println("Back to main personal menu");
					break;

				}

			} while (choice != 0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void registerNurse(JDBCPersonalManager personalManager, JDBCContractManager contractManager) {

		Personal loggedInPersonal = personalManager.searchPersonalByEmail(this.email);
	
		if (loggedInPersonal != null) {
			return;

		}
		System.out.println("\nYOU ARE IN OPTION 1, REGISTER PLEASE:");

		System.out.println("Type your name");
		String name = Utilities.readString();

		System.out.println("Type your surname ");
		String surname = Utilities.readString();

		System.out.println("Establishing a contract");
		Contract contract = Utilities.makeAContract();
		Contract contractAdded = contractManager.addContract(contract); // SQL DONE

		System.out.println("Please introduce a photo  by typing the file path");
		String photoPath = Utilities.readString();
		byte[] photo = Utilities.readImage(photoPath);

		Personal nurse = new Personal(name, surname, this.email, contractAdded, photo);

		personalManager.addPersonal(nurse);// SQL DONE

	}

	private void modifyNurse(JDBCPersonalManager personalManager) {

		Personal nurseModifying = personalManager.searchPersonalByEmail(this.email); 

		if (nurseModifying != null) {

			System.out.println("Moidfy your name :");
			String newName = Utilities.readString();

			System.out.println("Modify your surname :");
			String newSurname = Utilities.readString();

			System.out.println("Please introduce a new photo  by typing the file path");
			String photoPath = Utilities.readString();
			byte[] newPhoto = Utilities.readImage(photoPath);

			personalManager.modifyPersonal(nurseModifying, newName, newSurname, this.email, newPhoto);
		}
	}

	private void deleteNurse(JDBCPersonalManager personalManager) {

		Personal nurseDeleteing = personalManager.searchPersonalByEmail(this.email); // sql done

		if (nurseDeleteing != null) {
			System.out.println("Introduce the id of the nurse you want to delete");
			Integer id_nurse_deleted = Utilities.readInteger("Introduce the id of the nurse you want to delete");
			personalManager.deletePersonalByID(id_nurse_deleted);// SQL DONE
		}
	}

	private void listPersonal(JDBCPersonalManager personalManager) {

		Personal nurseDeleteingPersonal = personalManager.searchPersonalByEmail(this.email); // sql done

		if (nurseDeleteingPersonal != null) {
			System.out.println("List of nurses registered ordered by id:");
			List<Personal> nurses = personalManager.listPersonal();
			for (Personal nurse : nurses) {
				System.out.println(nurse);
			}
		}

	}

	private void registerDonor(JDBCPersonalManager personalManager, JDBCDonorManager donorManager)
			throws ParseException {
		Personal loggedInPersonal = personalManager.searchPersonalByEmail(this.email);

		if (loggedInPersonal != null) { // if there is some nurse the registration can be done

			System.out.println("Donor´s name :");
			String name = Utilities.readString();

			System.out.println("Donor´s surname :");
			String surname = Utilities.readString();

			System.out.println("Donor´s dob d");
			LocalDate dob = Utilities.getDateFromKeyboard();
			Date dobSql = Date.valueOf(dob);

			String bloodType = Utilities.askBloodType("Donor´s blood type");

			Integer timesDonated = 0;
			Donor donorRegistered = new Donor(name, surname, dobSql, bloodType, timesDonated);

			donorManager.addDonor(donorRegistered);

		}
	}

	private void modifyDonor(JDBCPersonalManager personalManager, JDBCDonorManager donorManager) throws ParseException {

		Personal loggedInPersonal = personalManager.searchPersonalByEmail(this.email);

		if (loggedInPersonal != null) {
			System.out.println("Enter the donor ID you want to modify :");
			Integer idDonor = Utilities.readInteger("Enter the donor ID you want to modify :");

			Donor donorExisting = donorManager.getDonorByID(idDonor);

			if (donorExisting != null) {

				System.out.println("Donor´s name modification:");
				String name = Utilities.readString();

				System.out.println("Donor´s surname modification :");
				String surname = Utilities.readString();

				System.out.println("Donor´s dob ");
				;
				LocalDate dob = Utilities.getDateFromKeyboard();
				Date dobSql = Date.valueOf(dob);

				String bloodType = Utilities.askBloodType("Donor´s blood type modification");

				int times = donorExisting.getTimes(); // not modified

				Donor donorModified = new Donor(idDonor, name, surname, dobSql, bloodType, times);
				donorManager.modifyDonor(donorModified);

				

			} else {
				System.out.println("No donor existing with that id ");
			}
		}

	}

	private void deleteDonor(JDBCPersonalManager personalManager, JDBCDonorManager donorManager) {
		Personal loggedInPersonal = personalManager.searchPersonalByEmail(this.email);

		if (loggedInPersonal != null) {
			System.out.println("Enter the donor ID you want to modify :");
			Integer idDonor = Utilities.readInteger("Enter the donor ID you want to delete :");
			Donor donorExisting = donorManager.getDonorByID(idDonor);

			if (donorExisting != null) {
				donorManager.deleteDonor(donorExisting.getId());
			} else {
				System.out.println("No donor existing with that id ");
			}
		}
	}

	private void listDonors(JDBCPersonalManager personalManager, JDBCDonorManager donorManager) {
		Personal loggedInPersonal = personalManager.searchPersonalByEmail(this.email);

		if (loggedInPersonal != null) {
			System.out.println("List of donors ordered by name:");
			List<Donor> donors = donorManager.listDonorsByName();
			for (Donor donor : donors) {
				System.out.println(donor);
			}
		}
	}

	private void addDonation(JDBCPersonalManager personalManager, JDBCDonorManager donorManager,
			JDBCDonationManager donationManager, JDBCBloodManager bloodManager, JDBCContractManager contractManager,
			XMLManager xmlManager) {

		if (!personalManager.isPersonalTableNotEmpty()) {
			System.out.println(
					"No personal registered in the blood bank database for performing the extraction of blood.");
			System.out.println("Redirecting to the main menu...");
			personalMenuOptions(this.email, bloodManager, contractManager, donationManager, donorManager,
					personalManager, xmlManager);
			return;
		}

		System.out.println("Type the email of the nurse who has performed the extraction of blood ");
		String email = Utilities.readString();

		Personal nurseAttending = personalManager.searchPersonalByEmail(email); // it could be any personal (nurse) not
		if (nurseAttending == null) {
			System.out.println("Redirecting to the main menu...");
			personalMenuOptions(this.email, bloodManager, contractManager, donationManager, donorManager,
					personalManager, xmlManager);
			return;
		}

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
		Donation donationAdded = donationManager.addDonation(newDonation);
		
		donorManager.incrementDonorTimes(donorDonating); // the donor´s times donating increment 1

		Blood newBlood = new Blood(donorDonating.getBloodtype(), amountDonating, currentDate);

		Blood bloodAdded = bloodManager.addBlood(newBlood);


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
		}
	}

	private void listDonations(JDBCPersonalManager personalManager, JDBCDonationManager donationManager) {
		Personal loggedInPersonal = personalManager.searchPersonalByEmail(this.email);

		if (loggedInPersonal != null) {
			System.out.println("List of donations ordered by date:");
			List<Donation> donations = donationManager.getDonationsByDate();

			for (Donation donation : donations) {
				System.out.println(donation);
			}
		}
	}

	private void listOfBloodExtractions(JDBCPersonalManager personalManager, JDBCBloodManager bloodManager) {
		Personal loggedInPersonal = personalManager.searchPersonalByEmail(this.email);

		if (loggedInPersonal != null) {
			System.out.println("List of blood stock ordered by blood type:");
			List<Blood> bloods = bloodManager.getBloodListByType();
			for (Blood blood : bloods) {
				System.out.println(blood);
			}
		}
	}

	private void printMe(JDBCPersonalManager personalManager, XMLManager xmlManager) {
		Personal loggedInPersonal = personalManager.searchPersonalByEmail(this.email);

		if (loggedInPersonal != null) {
			System.out.println("How do you want to save the data?");
			System.out.println("1. XML");
			System.out.println("2. HTML");
			int choice = Utilities.readInteger("choice");

			switch (choice) {
			case 1:
				xmlManager.personal2xml(loggedInPersonal.getId());
				System.out.println("Personal data saved as XML file.");
				break;
			case 2:

				xmlManager.personalTransformerToHTML(loggedInPersonal.getId());
				System.out.println("Personal data saved as HTML file.");
				break;
			default:
				System.out.println("Invalid option. Please choose xml or html.");
			}

		}

	}

	private void loadPersonal(JDBCPersonalManager personalManager, XMLManager xmlManager) {
		Personal person = null;
		Personal loggedInPersonal = personalManager.searchPersonalByEmail(this.email);

		File dir = new File("xmls");
		String fileName = "PersonID" + loggedInPersonal.getId() + ".xml";
		File file = new File(dir, fileName);

		person = xmlManager.xml2Personal(file);
		System.out.println(person);

	}

	private void printDonations(JDBCPersonalManager personalManager, XMLManager xmlManager) {
		Personal loggedInPersonal = personalManager.searchPersonalByEmail(this.email);

		if (loggedInPersonal != null) {
			System.out.println("Introduce the blood type to print that list to an XML or HTML file:");
			String bloodType = Utilities.askBloodType("blood type");

			System.out.println("How do you want to save the data?");
			System.out.println("1. XML");
			System.out.println("2. HTML");
			int choice = Utilities.readInteger("choice");

			switch (choice) {
			case 1:
				xmlManager.donation2xml(bloodType);
				System.out.println("Donation data saved as XML file.");
				break;
			case 2:

				xmlManager.donationTransformerToHTML(bloodType);
				System.out.println("Donation data saved as HTML file.");
				break;
			default:
				System.out.println("Invalid option. Please choose xml or html.");
			}
		}
	}

	private void loadDonations(JDBCPersonalManager personalManager, XMLManager xmlManager) {
		Donation donation = null;

		System.out.println("Introduce the blood type list you want to load  (if it has printed first)");
		String bloodType = Utilities.askBloodType("blood type");

		File dir = new File("xmls");
		String fileName = "DonationBloodType" + bloodType + ".xml";
		File file = new File(dir, fileName);

		donation = xmlManager.xml2Donation(file);
		System.out.println(donation);
	}

}
