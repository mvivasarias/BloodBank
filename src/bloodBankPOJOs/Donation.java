package bloodBankPOJOs;

import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import bloodBankXMLutils.SQLDateAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Donation")
public class Donation  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8609950568446520203L;
	@XmlTransient
	private Integer id;
	@XmlElement
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date date;
	@XmlAttribute
	private float amount;
	@XmlElement
	private Donor donor;
	@XmlElement (name="nurse")
	private Personal personal;
	@XmlTransient
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

	public Donation(Integer id, Date date, float amount, Donor donor, Personal personal, List<Blood> bloods) {
		super();
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.donor = donor;
		this.personal = personal;
		this.bloods = bloods;
	}
	

	public Donation( Date date, float amount, Donor donor, Personal personal) {
		super();
		this.date = date;
		this.amount = amount;
		this.donor = donor;
		this.personal = personal;
		bloods = new ArrayList<Blood>();
	}
	

	public Donation(Integer id, Date date, float amount, Donor donor, Personal personal) {
		super();
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.donor = donor;
		this.personal = personal;
		this.bloods = new ArrayList<Blood>();
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

	
	

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
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



