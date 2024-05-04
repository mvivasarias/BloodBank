package BloodBankPOJOs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Blood implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3464987077094646032L;
	
	private String id;
	private String blood;
	private Hospital hospital;
	private Stock stock;
	private List<Donation> donations;
	
	public Blood() {
		super();
		donations = new ArrayList<Donation>();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

	public List<Donation> getDonations() {
		return donations;
	}
	

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}

	@Override
	public int hashCode() {
		return Objects.hash(blood, donations, hospital, id, stock);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Blood other = (Blood) obj;
		return Objects.equals(blood, other.blood) && Objects.equals(donations, other.donations)
				&& Objects.equals(hospital, other.hospital) && Objects.equals(id, other.id)
				&& Objects.equals(stock, other.stock);
	}

	@Override
	public String toString() {
		return "Blood [id=" + id + ", blood=" + blood + ", hospital=" + hospital + ", stock=" + stock + "]";
	}


	
}


