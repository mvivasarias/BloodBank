package bloodBankPOJOs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Donation  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8609950568446520203L;
	
	private Integer id;
	private Date date;
	private Integer amount;
	private Donor donor;
	private Personal personal;
	private List<Blood> bloods;
	
	public Donation() {
		super();
		bloods = new ArrayList<Blood>();
	}
	
	public Donation(Date date, Integer amount, Donor donor, Personal personal, List<Blood> bloods) {
		super();
		this.date = date;
		this.amount = amount;
		this.donor = donor;
		this.personal = personal;
		this.bloods = bloods;
	}

	public Donation(Integer id, Date date, Integer amount, Donor donor, Personal personal, List<Blood> bloods) {
		super();
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.donor = donor;
		this.personal = personal;
		this.bloods = bloods;
	}
	

	public Donation( Date date, Integer amount, Donor donor, Personal personal) {
		super();
		this.date = date;
		this.amount = amount;
		this.donor = donor;
		this.personal = personal;
		bloods = new ArrayList<Blood>();
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	

	public Donor getDonor() {
		return donor;
	}



	public void setDonor(Donor donor) {
		this.donor = donor;
	}


	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	public List<Blood> getBloods() {
		return bloods;
	}

	public void setBloods(List<Blood> bloods) {
		this.bloods = bloods;
	}


	@Override
	public int hashCode() {
		return Objects.hash(amount, bloods, date, donor, id, personal);
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
		return Objects.equals(amount, other.amount) && Objects.equals(bloods, other.bloods)
				&& Objects.equals(date, other.date) && Objects.equals(donor, other.donor)
				&& Objects.equals(id, other.id) && Objects.equals(personal, other.personal);
	}

	@Override
	public String toString() {
		return "Donation [id=" + id + ", date=" + date + ", amount=" + amount + ", donor=" + donor + ", personal="
				+ personal + ", bloods=" + bloods + "]";
	}

	

	
}



