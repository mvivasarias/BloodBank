package bloodBankPOJOs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Blood implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3464987077094646032L;

	private Integer id;
	private String bloodType;
	private Stock stock;
	private List<Donation> donations;
	private List<Hospital> hospitals;

	public Blood() {
		super();
		donations = new ArrayList<Donation>();
		hospitals = new ArrayList<Hospital>();
	}

	public Blood(String type, Stock stock, List<Donation> donations, List<Hospital> hospitals) {
		super();
		this.bloodType = type;
		this.stock = stock;
		this.donations = donations;
		this.hospitals = hospitals;
	}

	public Blood(Integer id, String type, Stock stock, List<Donation> donations, List<Hospital> hospitals) {
		super();
		this.id = id;
		this.bloodType = type;
		this.stock = stock;
		this.donations = donations;
		this.hospitals = hospitals;
	}
	

	public Blood(String bloodType) {
		super();
		this.bloodType = bloodType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return bloodType;
	}

	public void setType(String type) {
		this.bloodType = type;
	}

	public List<Donation> getDonations() {
		return donations;
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

	public List<Hospital> getHospitals() {
		return hospitals;
	}

	public void setHospitals(List<Hospital> hospitals) {
		this.hospitals = hospitals;
	}

	@Override
	public int hashCode() {
		return Objects.hash(donations, hospitals, id, stock, bloodType);
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
		return Objects.equals(donations, other.donations) && Objects.equals(hospitals, other.hospitals)
				&& Objects.equals(id, other.id) && Objects.equals(stock, other.stock)
				&& Objects.equals(bloodType, other.bloodType);
	}

	@Override
	public String toString() {
		return "Blood [id=" + id + ", type=" + bloodType + ", stock=" + stock + "]";
	}

}
