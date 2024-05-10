package ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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
	
	private static BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
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
								
				switch(choice)
				{
				case 1: 
					login();	
					//login (identifies hospital manager automatically by credentials)
				case 2:
					System.out.println("Add info of new user.");
					//signUpUser();
					//sign up 
				case 3: 
					System.out.println("Udpate the password of an exissting user.");
					//updatePassword();
				case 0:
					System.out.println("Exiting application.");
					//jdbcmanager.disconnect();
				}
				
			}while(choice!=0);
			
			
		}catch(Exception e)
		{e.printStackTrace();}
	}
	
	private static void login()throws Exception{
		System.out.println("Email: ");
		String email = reader.readLine();
		
		System.out.println("Password: ");
		String password = reader.readLine();
		
		//User u= usermanager.checkPassword(email, password);
		//Falta crear método checkPassword() en JPAuserManager.java
		
		if(u!=null & u.getRole().getName().equals(//Hospital manager role))
		{
			System.out.println("Hospital login");
			//call for owner submenu;
			hospitalManagerMenu(email);
		}
	}
	
	private static void hospitalManagerMenu(String email) {
	
		try {
			int choice;
			do {
				System.out.println("Choose an option");
				System.out.println("1. Check blood stock"); 
				System.out.println("2. Perform a request.");
				System.out.println("3. Check requests");//New option for checking request status (accepted/pending/denied) in case we use a request menu
				System.out.println("0. Return.");
				
				choice = Integer.parseInt(reader.readLine());
								
				switch(choice)
				{
				case 1: 
					//check blood stock 
					break;
				case 2:
					//perform request / get blood / interaction between hospital and blood
				case 3:
					//In case we use a request menu, we can use this case to check current & past request status
				
				case 0:
					System.out.println("Back to main menu");
					
				}
				
			}while(choice!=0);
			
			
		}catch(Exception e)
		{e.printStackTrace();}
	}
	
/*	private static void signUpUser() {
		
		try {
			System.out.println("Introduce email: ");
			String email = reader.readLine();
			System.out.println("Introduce the password");
			String password = reader.readLine();
			//We could implement a simple method to make the new passwords fit certain parameters
			
			MessageDigest md= MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] pass = md.digest();
			
			System.out.println("Introduce the role of the user. 1: , 2: "); //User roles
			Integer rol = Integer.parseInt(reader.readLine());
			Role r = usermanager.getRole(rol);
			
			User u = new User(email, pass, r);
			
			usermanager.newUser(u);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			}
	}*/
}
