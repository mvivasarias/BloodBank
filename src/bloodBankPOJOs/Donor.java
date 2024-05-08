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
	private Date date;
	private String type;
	private Integer times;
	private List<Donation> donations;
	
	public Donor() {
		super();
		this.donations = new ArrayList<Donation>();
	}
	

	public Donor(String name, String surname, Date date, String type, Integer times, List<Donation> donations) {
		super();
		this.name = name;
		this.surname = surname;
		this.date = date;
		this.type = type;
		this.times = times;
		this.donations = donations;
	}

	public Donor(Integer id, String name, String surname, Date date, String type, Integer times,
			List<Donation> donations) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.date = date;
		this.type = type;
		this.times = times;
		this.donations = donations;
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


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
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



