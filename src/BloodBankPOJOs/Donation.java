package BloodBankPOJOs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class Donation implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4804176946955355846L;
	
	private Integer id;
	private Date date;
	private Donor donor;
	private List<Blood> bloods;
	
	public Donation() {
		super();
		bloods = new ArrayList<Blood>();
	}

	public Donation(Date date, Donor donor, List<Blood> bloods) {
		super();
		this.date = date;
		this.donor = donor;
		this.bloods = bloods;
	}

	public Donation(Integer id, Date date, Donor donor, List<Blood> bloods) {
		super();
		this.id = id;
		this.date = date;
		this.donor = donor;
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

	public Donor getDonor() {
		return donor;
	}

	public void setDonor(Donor donor) {
		this.donor = donor;
	}

	public List<Blood> getBloods() {
		return bloods;
	}

	public void setBloods(List<Blood> bloods) {
		this.bloods = bloods;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bloods, date, donor, id);
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
		return Objects.equals(bloods, other.bloods) && Objects.equals(date, other.date)
				&& Objects.equals(donor, other.donor) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Donation [id=" + id + ", date=" + date + ", donor=" + donor + "]";
	}

	
}

