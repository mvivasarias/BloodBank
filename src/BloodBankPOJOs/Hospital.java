package BloodBankPOJOs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hospital implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2983952873784901800L;
	
	private Integer id;
	private String name;
	private String address;
	private List<Blood> bloods;
	
	public Hospital() {
		super();
		this.bloods = new ArrayList<Blood>();	
	}

	public Hospital(String name, String address, List<Blood> bloods) {
		super();
		this.name = name;
		this.address = address;
		this.bloods = bloods;
	}

	public Hospital(Integer id, String name, String address, List<Blood> bloods) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.bloods = bloods;
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

	public List<Blood> getBloods() {
		return bloods;
	}

	public void setBloods(List<Blood> bloods) {
		this.bloods = bloods;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, bloods, id, name);
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
		return Objects.equals(address, other.address) && Objects.equals(bloods, other.bloods)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Hospital [id=" + id + ", name=" + name + ", address=" + address + "]";
	}
	
	
}
