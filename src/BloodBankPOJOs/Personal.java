package BloodBankPOJOs;

import java.io.Serializable;
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
	
	//constructor sin el id
	public Personal(String name, String surname, Integer phone) {
		super();
		this.name = name;
		this.surname = surname;
		this.phone = phone;
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

	@Override
	public int hashCode() {
		return Objects.hash(id, name, phone, surname);
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
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(phone, other.phone)
				&& Objects.equals(surname, other.surname);
	}
	
	
	
	
	
	

}
