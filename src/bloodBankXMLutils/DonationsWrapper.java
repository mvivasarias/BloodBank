package bloodBankXMLutils;

import javax.xml.bind.annotation.*;

import bloodBankPOJOs.Donation;

import java.util.List;

@XmlRootElement(name = "donations")
@XmlAccessorType(XmlAccessType.FIELD)
public class DonationsWrapper {

	@XmlElement(name = "donation")
	private List<Donation> donations;

	public List<Donation> getDonations() {
		return donations;
	}

	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}
}
