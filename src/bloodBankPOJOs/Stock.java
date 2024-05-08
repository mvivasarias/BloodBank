package bloodBankPOJOs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Stock implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -786432011615587441L;
	
	private Integer id;
	private Date date;
	private Integer litters;
	private List<Blood> bloods;
	
	public Stock() {
		super();
		bloods = new ArrayList<Blood>();
	}

	public Stock(Date date, Integer litters, List<Blood> bloods) {
		super();
		this.date = date;
		this.litters = litters;
		this.bloods = bloods;
	}

	public Stock(Integer id, Date date, Integer litters, List<Blood> bloods) {
		super();
		this.id = id;
		this.date = date;
		this.litters = litters;
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

	public Integer getLitters() {
		return litters;
	}

	public void setLitters(Integer litters) {
		this.litters = litters;
	}

	public List<Blood> getBloods() {
		return bloods;
	}

	public void setBloods(List<Blood> bloods) {
		this.bloods = bloods;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bloods, date, id, litters);
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
				&& Objects.equals(litters, other.litters);
	}

	@Override
	public String toString() {
		return "Stock [id=" + id + ", date=" + date + ", litters=" + litters + "]";
	}

}
