package utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utilities {
	
	 public static String readString() {

	        while (true) {
	            try {

	                String stringLeido;
	                BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
	                stringLeido = consola.readLine();

	                return stringLeido;

	            } catch (IOException ex) {
	                System.out.println("ERROR reading String, please introduce it again");
	            }
	        }

	    }


	    public static int readInteger(String question) {
	        while (true) {
	            try {
	                String stringLeido;
	                BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
	                stringLeido = consola.readLine();
	                return Integer.parseInt(stringLeido);

	            } catch (NumberFormatException | IOException ex) {
	                System.out.println("ERROR reading Integer, please introduce it again");
	            }
	        }
	    }


	    public static int readIntegerWithinRanges(int begin, int end) {
	        while (true) {
	            try {
	                String stringLeido;
	                BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
	                stringLeido = consola.readLine();
	                int ent = Integer.parseInt(stringLeido);
	                if (ent >= begin || ent <= end) {
	                    return ent;
	                } else {
	                    System.out.println("Not between ranges");

	                }

	            } catch (IOException ex) {
	                System.out.println("ERROR" + ex);
	            }
	        }
	    }

	    public static float readfloat() {

	        while (true) {
	            try {

	                String stringLeido;
	                BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
	                stringLeido = consola.readLine();
	                return Float.parseFloat(stringLeido);

	            } catch (NumberFormatException | IOException ex) {
	                System.out.println("ERROR reading float, please introduce it again");
	            }
	        }

	    }

	public static String askBloodType(String question) {

		while (true) {
			System.out.println(question + "\n   1. A+" + "\n   2. A-" + "\n   3. B+" + "\n   4. B-"
					+ "\n   5. AB+" + "\n   6. AB-" + "\n   7. 0+" + "\n   8. 0-");
			int option = readInteger("  Option: ");

			switch (option) {
				case 1: 
					return "A+";
				case 2:	
					return "A-";
				case 3:	
					return "B+";
				case 4:	
					return "B-";
				case 5:	
					return "AB+";
				case 6:	
					return "AB-";
				case 7:	
					return "0+";
				case 8:	
					return "0-";
				default: 
					System.out.println("ERROR: Option not valid");
			}
		}
	}

}
