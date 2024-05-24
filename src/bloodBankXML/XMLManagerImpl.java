package bloodBankXML;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import bloodBankIfaces.XMLManager;
import bloodBankJDBC.JDBCDonationManager;
import bloodBankJDBC.JDBCManager;
import bloodBankJDBC.JDBCPersonalManager;
import bloodBankPOJOs.Donation;
import bloodBankPOJOs.Personal;
import bloodBankXMLutils.DonationsWrapper;

public class XMLManagerImpl implements XMLManager {
	JDBCManager manager;
	JDBCPersonalManager personalManager;
	JDBCDonationManager donationManager;

	@Override
	public void personal2xml(Integer id) {

		Personal person = null;
		List<Donation> donations = new ArrayList<Donation>();

		manager = new JDBCManager();
		personalManager = new JDBCPersonalManager(manager);
		donationManager = new JDBCDonationManager(manager);

		try {
			// search for the person
			person = personalManager.searchPersonalByID(id);

			// search for donations of a person
			donations = donationManager.getDonationsOfaPersonal(id);
			person.setDonations(donations);

			// export the person to an xml file
			JAXBContext jaxbContext = JAXBContext.newInstance(Personal.class);
			Marshaller marshaller = jaxbContext.createMarshaller();

			// set the object and the name of the file
			File file = new File("/xmls/PersonID"+id+".xml");
			marshaller.marshal(person, file);

			System.out.println("Printed personal class to an XML file!");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void donation2xml(String bloodType) {
		List<Donation> donationsToXml = null;
		Personal person = null;

		manager = new JDBCManager();
		personalManager = new JDBCPersonalManager(manager);
		donationManager = new JDBCDonationManager(manager);

		try {
			// search for all the donations with a blood type
			donationsToXml = donationManager.getDonationsByBloodType(bloodType);

			// search for the person with the given id to have the object not only the id
			for (Donation donation : donationsToXml) {
				person = personalManager.searchPersonalByID(donation.getPersonal().getId());
				donation.setPersonal(person);
			}

			// export the person to an xml file
			JAXBContext jaxbContext = JAXBContext.newInstance(Donation.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // To format the XML

			// Wrap the list of donations for JAXB
			DonationsWrapper wrapper = new DonationsWrapper();
			wrapper.setDonations(donationsToXml);

			// Set the object and the name of the file
			File file = new File("/xmls/Donations"+bloodType+".xml");
			marshaller.marshal(wrapper, file);
			// set the object and the name of the file

			System.out.println("Printed  donation class to an XML file!");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Donation xml2Donation(File xml) {
		Donation donation = null;

		manager = new JDBCManager();
		donationManager = new JDBCDonationManager(manager);

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Donation.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			donation = (Donation) unmarshaller.unmarshal(xml);
			donationManager.addDonation(donation);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return donation;
	}

	@Override
	public Personal xml2Personal(File xml) {
		Personal person = null;
		manager = new JDBCManager();
		personalManager = new JDBCPersonalManager(manager);

		try {
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Donation.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			person = (Personal) unmarshaller.unmarshal(xml);
			personalManager.addPersonal(person);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return person;
	}

}
