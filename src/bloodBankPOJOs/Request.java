package bloodBankPOJOs;

import java.io.Serializable;
import java.sql.Date;// Confirmadme porfa que es este
import java.util.Objects;

public class Request implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private float liters;
	private Date date;
	private String bloodType;
	
	public Request() {
		super();
	}
	
	public Request(float liters, Date date, String bloodType) {
		super();
		this.liters = liters;
		this.date = date;
		this.bloodType = bloodType;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	
	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bloodType, date,id, liters);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		return Objects.equals(bloodType, other.bloodType) && Objects.equals(date, other.date)
				&& Objects.equals(id, other.id)
				&& Float.floatToIntBits(liters) == Float.floatToIntBits(other.liters);
	}

	@Override
	public String toString() {
		return "Request [liters=" + liters + ", date=" + date + ", bloodType=" + bloodType + "]";
	}

	
	
	
	
}