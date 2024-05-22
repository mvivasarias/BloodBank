package bloodBankPOJOs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;




public class Personal implements Serializable {

	private static final long serialVersionUID = 6714833338820944413L;
	private Integer id;
	private String name;
	private String surname;
	private String email;
	private Contract contract;
	private byte[] photo;
	private List<Donation> donations;

	public Personal() {
		super();
		donations = new ArrayList<Donation>();

	}

	// constructor sin el id
	public Personal(String name, String surname, String email, List<Donation> donations, Contract contract,
			byte[] photo) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.donations = donations;
		this.contract = contract;
		this.photo = photo;
	}

	public Personal(String name, String surname, String email, Contract contract, byte[] foto) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.contract = contract;
		this.photo = foto;
	}

	public Personal(Integer id, String name, String surname, String email, Contract contract, byte[] foto) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.contract = contract;
		this.photo = foto;
		this.donations = new ArrayList<Donation>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public List<Donation> getDonations() {
		return donations;
	}

	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(photo);
		result = prime * result + Objects.hash(contract, donations, id, name, email, surname);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personal other = (Personal) obj;
		return Objects.equals(contract, other.contract) && Objects.equals(donations, other.donations)
				&& Arrays.equals(photo, other.photo) && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(email, other.email) && Objects.equals(surname, other.surname);
	}

	@Override
	public String toString() {
		return "Personal [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + ", contract="
				+ contract + ", photo=" + Arrays.toString(photo) + "]";
	}

}
