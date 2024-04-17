package BloodBankPOJOs;

import java.io.Serializable;
import java.util.Objects;

public class Hospital implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2983952873784901800L;
	
	private Integer id;
	private String name;
	private String address;
	
	public Hospital(String name, String address) {
		super();
		this.name = name;
		this.address = address;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hospital other = (Hospital) obj;
		return Objects.equals(address, other.address) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name);
	}
	
}
