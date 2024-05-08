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
	private List<Personal> personals;
	
	public Donor() {
		super();
		personals = new ArrayList<Personal>();
	}
	

	

	public Donor(String name, String surname, Date dob, String bloodtype, Integer times, List<Personal> personals) {
		super();
		this.name = name;
		this.surname = surname;
		this.dob = dob;
		this.bloodtype = bloodtype;
		this.times = times;
		this.personals = personals;
	}



	public Donor(Integer id, String name, String surname, Date dob, String bloodtype, Integer times,
			List<Personal> personals) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.dob = dob;
		this.bloodtype = bloodtype;
		this.times = times;
		this.personals = personals;
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




	public List<Personal> getPersonals() {
		return personals;
	}




	public void setPersonals(List<Personal> personals) {
		this.personals = personals;
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
		return Objects.hash(date, donations, id, name, surname, times, type);
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
		return Objects.equals(date, other.date) && Objects.equals(donations, other.donations)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(surname, other.surname) && Objects.equals(times, other.times)
				&& Objects.equals(type, other.type);
	}


	@Override
	public String toString() {
		return "Donor [id=" + id + ", name=" + name + ", surname=" + surname + ", date=" + date + ", type=" + type
				+ ", times=" + times + "]";
	}
	

}



