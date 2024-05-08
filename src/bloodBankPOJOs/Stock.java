package bloodBankPOJOs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Stock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6379275603644441738L;
	
	private Integer id;
	private Date date;
	private Float liters;
	private List<Blood> bloods;
	
	public Stock() {
		super();
		this.bloods = new ArrayList<Blood>();
	}

	public Stock(Date date, Float liters, List<Blood> bloods) {
		super();
		this.date = date;
		this.liters = liters;
		this.bloods = bloods;
	}
	

	public Stock(Integer id, Date date, Float liters, List<Blood> bloods) {
		super();
		this.id = id;
		this.date = date;
		this.liters = liters;
		this.bloods = bloods;
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

	public List<Blood> getBloods() {
		return bloods;
	}

	public void setBloods(List<Blood> bloods) {
		this.bloods = bloods;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bloods, date, id, liters);
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
		return Objects.equals(bloods, other.bloods) && Objects.equals(date, other.date) && Objects.equals(id, other.id)
				&& Objects.equals(liters, other.liters);
	}

	@Override
	public String toString() {
		return "Stock [id=" + id + ", date=" + date + ", liters=" + liters + "]";
	}
	
	
}
	
