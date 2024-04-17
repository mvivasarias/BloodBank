package BloodBankPOJOs;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Stock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6379275603644441738L;
	
	private Integer id;
	private Date date;
	private Float liters;
	
	public Stock(Date date, Float liters) {
		super();
		this.date = date;
		this.liters = liters;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Float getLiters() {
		return liters;
	}

	public void setLiters(Float liters) {
		this.liters = liters;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, id, liters);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		return Objects.equals(date, other.date) && Objects.equals(id, other.id) && Objects.equals(liters, other.liters);
	}
	
	
	
	
}
