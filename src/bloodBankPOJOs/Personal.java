package bloodBankPOJOs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class Personal implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6714833338820944413L;
	
	private Integer id;
	private String name;
	private String surname;
	private Integer phone;
	private List<Donor> donors;
	private List<Hospital> hospital;
	
	private Contract contract;
	private Byte[] foto;
	
	public Personal() {
		super();
		donors = new ArrayList<Donor>();
		hospital = new ArrayList<Hospital>();
		
	}
	
	//constructor sin el id

	public Personal(String name, String surname, Integer phone, List<Donor> donors, List<Hospital> hospital,
			Contract contract, Byte[] foto) {
		super();
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.donors = donors;
		this.hospital = hospital;
		this.contract = contract;
		this.foto = foto;
	}
	

	public Personal(Integer id, String name, String surname, Integer phone, List<Donor> donors, List<Hospital> hospital,
			Contract contract, Byte[] foto) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.donors = donors;
		this.hospital = hospital;
		this.contract = contract;
		this.foto = foto;
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

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}
	

	public Byte[] getFoto() {
		return foto;
	}

	public void setFoto(Byte[] foto) {
		this.foto = foto;
	}
	

	public List<Donor> getDonors() {
		return donors;
	}

	public void setDonors(List<Donor> donors) {
		this.donors = donors;
	}

	public List<Hospital> getHospital() {
		return hospital;
	}

	public void setHospital(List<Hospital> hospital) {
		this.hospital = hospital;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(foto);
		result = prime * result + Objects.hash(contract, donors, hospital, id, name, phone, surname);
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
		return Objects.equals(contract, other.contract) && Objects.equals(donors, other.donors)
				&& Arrays.equals(foto, other.foto) && Objects.equals(hospital, other.hospital)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(phone, other.phone) && Objects.equals(surname, other.surname);
	}

	@Override
	public String toString() {
		return "Personal [id=" + id + ", name=" + name + ", surname=" + surname + ", phone=" + phone + ", contract="
				+ contract + ", foto=" + Arrays.toString(foto) + "]";
	}

	
}
