package bloodBankPOJOs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Donor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2082848644338120173L;
	
	private Integer id;
	private String name;
	private String surname;
	private Date dob;
	private String bloodtype;
	private Integer times;
	private List<Donation> donations;
	
	public Donor() {
		super();
		donations = new ArrayList<Donation>();
	}

	public Donor(String name, String surname, Date dob, String bloodtype, Integer times, List<Donation> donations) {
		super();
		this.name = name;
		this.surname = surname;
		this.dob = dob;
		this.bloodtype = bloodtype;
		this.times = times;
		this.donations = donations;
	}

	public Donor(Integer id, String name, String surname, Date dob, String bloodtype, Integer times) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.dob = dob;
		this.bloodtype = bloodtype;
		this.times = times;
		this.donations = new ArrayList<Donation>();

	}
	
	

	public Donor(String name, String surname, Date dob, String bloodtype) {
		super();
		this.name = name;
		this.surname = surname;
		this.dob = dob;
		this.bloodtype = bloodtype;
		this.donations=new ArrayList<Donation>();
	}

	public Donor(String name, String surname, Date dob, String bloodtype, Integer times) {
		super();
		this.name = name;
		this.surname = surname;
		this.dob = dob;
		this.bloodtype = bloodtype;
		this.times = times;
		this.donations=new ArrayList<Donation>();
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getBloodtype() {
		return bloodtype;
	}

	public void setBloodtype(String bloodtype) {
		this.bloodtype = bloodtype;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public List<Donation> getDonations() {
		return donations;
	}

	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bloodtype, dob, donations, id, name, surname, times);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Donor other = (Donor) obj;
		return Objects.equals(bloodtype, other.bloodtype) && Objects.equals(dob, other.dob)
				&& Objects.equals(donations, other.donations) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(surname, other.surname)
				&& Objects.equals(times, other.times);
	}

	@Override
	public String toString() {
		return "Donor [id=" + id + ", name=" + name + ", surname=" + surname + ", dob=" + dob + ", bloodtype="
				+ bloodtype + ", times=" + times + "]";
	}
	
	

}