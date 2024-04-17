package BloodBankPOJOs;

import java.io.Serializable;
import java.util.Objects;

public class Blood implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3464987077094646032L;
	
	private String id;
	private String blood;
	
	public Blood(String blood) {
		super();
		this.blood = blood;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

	@Override
	public int hashCode() {
		return Objects.hash(blood, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Blood other = (Blood) obj;
		return Objects.equals(blood, other.blood) && Objects.equals(id, other.id);
	}
	
	
	
}
