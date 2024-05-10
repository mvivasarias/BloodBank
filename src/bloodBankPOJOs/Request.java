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
	private Hospital hospital;
	private Blood blood;
	
	public Request() {
		super();
	}
	
	public Request(float liters, Date date, Hospital hospital, Blood blood) {
		super();
		this.liters = liters;
		this.date = date;
		this.hospital = hospital;
		this.blood = blood;
		
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

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public Blood getBlood() {
		return blood;
	}

	public void setBlood(Blood blood) {
		this.blood = blood;
	}

	@Override
	public int hashCode() {
		return Objects.hash(blood, date, hospital, id, liters);
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
		return Objects.equals(blood, other.blood) && Objects.equals(date, other.date)
				&& Objects.equals(hospital, other.hospital) && Objects.equals(id, other.id)
				&& Float.floatToIntBits(liters) == Float.floatToIntBits(other.liters);
	}

	@Override
	public String toString() {
		return "Request [liters=" + liters + ", date=" + date + ", hospital=" + hospital + ", blood=" + blood + "]";
	}

	
	
	
	
}