package ui;

public class HospitalMenu {
	
	public void hospitalManagerMenu(String email) {

		try {
			int choice;
			do {
				System.out.println("Choose an option");
				System.out.println("1. Check blood stock");
				System.out.println("2. Perform a request.");
				System.out.println("3. Check requests");// New option for checking request status
														// (accepted/pending/denied) in case we use a request menu
				System.out.println("0. Return.");

				choice = Integer.parseInt(reader.readLine());

				switch (choice) {
				case 1:
					// check blood stock
					break;
				case 2:
					// perform request / get blood / interaction between hospital and blood
				case 3:
					// In case we use a request menu, we can use this case to check current & past
					// request status

				case 0:
					System.out.println("Back to main menu");

				}

			} while (choice != 0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
