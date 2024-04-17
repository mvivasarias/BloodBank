package BloodBankPOJOs;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Donation implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4804176946955355846L;

	
	private Integer id;
	private Date date;
	
	public Donation(Date date) {
		super();
		this.date = date;
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

	@Override
	public int hashCode() {
		return Objects.hash(date, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Donation other = (Donation) obj;
		return Objects.equals(date, other.date) && Objects.equals(id, other.id);
	}
	
	
	
	
}
