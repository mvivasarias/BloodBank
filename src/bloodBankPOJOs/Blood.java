package bloodBankPOJOs;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import bloodBankXMLutils.SQLDateAdapter;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Blood")
public class Blood implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3464987077094646032L;
	@XmlTransient
	private Integer id;
	@XmlAttribute (name="BloodType")
	private String bloodType;
	@XmlElement
	private float liters;
	@XmlElement
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date date;
	@XmlTransient
	private List<Donation> donations;
	@XmlTransient
	private List<Hospital> hospitals;
	
	public Blood() {
		super();
		donations = new ArrayList<Donation>();
		hospitals = new ArrayList<Hospital>();
	}
	

	public Blood(Integer id, String bloodType, float liters, Date date) {
		super();
		this.id = id;
		this.bloodType = bloodType;
		this.liters = liters;
		this.date = date;
		donations = new ArrayList<Donation>();
		hospitals = new ArrayList<Hospital>();
	}


	public Blood(String bloodType, float liters, Date date) {
		super();
		this.bloodType = bloodType;
		this.liters = liters;
		this.date = date;
		donations = new ArrayList<Donation>();
		hospitals = new ArrayList<Hospital>();
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




	public String getBloodType() {
		return bloodType;
	}


	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}


	public List<Donation> getDonations() {
		return donations;
	}


	public float getLiters() {
		return liters;
	}


	public void setLiters(float liters) {
		this.liters = liters;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
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
		return Objects.hash(bloodType, date, donations, hospitals, id, liters);
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
		return Objects.equals(bloodType, other.bloodType) && Objects.equals(date, other.date)
				&& Objects.equals(donations, other.donations) && Objects.equals(hospitals, other.hospitals)
				&& Objects.equals(id, other.id) && Float.floatToIntBits(liters) == Float.floatToIntBits(other.liters);
	}


	@Override
	public String toString() {
		return "Blood [id=" + id + ", bloodType=" + bloodType + ", liters=" + liters + ", date=" + date + ", donations="
				+ donations + ", hospitals=" + hospitals + "]";
	}
	

}
