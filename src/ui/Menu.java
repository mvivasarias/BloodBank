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
					//login();					
				case 2:
					System.out.println("Add info of new user.");
					//signUpUser();
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
}
