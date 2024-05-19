package bloodBankPOJOs;

import java.io.Serializable;
import java.sql.Date;// Confirmadme porfa que es este
import java.util.Objects;

public class Request implements Serializable{

	
	private static final long serialVersionUID = 6714833338820944413L;
	
	private Hospital hospital;
	private Blood blood;
	private float liters;
	private Date date;
	
	
	public Request() {
		super();
	}
	
	
	

	public Request(Hospital hospital, Blood blood, float liters, Date date) {
		super();
		this.hospital = hospital;
		this.blood = blood;
		this.liters = liters;
		this.date = date;
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

	

	public Blood getBlood() {
		return blood;
	}

	public void setBlood(Blood blood) {
		this.blood = blood;
	}
	

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}


	@Override
	public int hashCode() {
		return Objects.hash(blood, date, hospital, liters);
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
				&& Objects.equals(hospital, other.hospital)
				&& Float.floatToIntBits(liters) == Float.floatToIntBits(other.liters);
	}


	@Override
	public String toString() {
		return "Request [Hospital=" + this.hospital  + ", date=" + this.date + ",liters=" + this.liters + ", blood type=" + blood.getBloodType() + "]";
	}

	
	
	
	
}