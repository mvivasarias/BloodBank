package bloodBankXML;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import bloodBankIfaces.XMLManager;
import bloodBankJDBC.JDBCDonationManager;
import bloodBankJDBC.JDBCManager;
import bloodBankJDBC.JDBCPersonalManager;
import bloodBankPOJOs.Blood;
import bloodBankPOJOs.Donation;
import bloodBankPOJOs.Personal;

public class XMLManagerImpl implements XMLManager {
	JDBCManager manager;
	JDBCPersonalManager personalManager;
	JDBCDonationManager donationManager;
	@Override
	public void personal2xml(Integer id) {
		
		Personal person = null;
		List<Donation> donations = new ArrayList<Donation>();
		
		manager = new JDBCManager();
		personalManager= new JDBCPersonalManager(manager);
		donationManager= new JDBCDonationManager(manager);
		

		try { 
			//search for the person
			person=personalManager.searchPersonalByID(id);
			//search for donations of a person
			donations=donationManager.getDonationsOfaPersonal(id);
			person.setDonations(donations);
			
			//export the person to an xml file
			JAXBContext jaxbContext= JAXBContext.newInstance(Personal.class);
			Marshaller marshaller= jaxbContext.createMarshaller();
			
			//set the object and the name of the file
			File file= new File("./xmls/Person.xml");
			marshaller.marshal(person, file);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Blood xml2Blood(File xml) {
		// TODO Auto-generated method stub
		return null;
	}

}
