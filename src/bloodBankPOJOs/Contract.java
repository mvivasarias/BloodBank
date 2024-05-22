package bloodBankPOJOs;

import java.io.Serializable;
import java.util.Objects;

public class Contract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7520806989904275224L;
	
	private Integer id;
	private Integer salary;
	private Integer hours;
	private Personal personal;   
	
	public Contract() {
		super();
	
	}

	public Contract(Integer salary, Integer hours, Personal personal) {
		super();
		this.salary = salary;
		this.hours = hours;
		this.personal = personal;
	}

	public Contract(Integer id, Integer salary, Integer hours) {
		super();
		this.id = id;
		this.salary = salary;
		this.hours = hours;
		this.personal = new Personal();
	}
	

	public Contract(Integer salary, Integer hours) {
		super();
		this.salary = salary;
		this.hours = hours;
		this.personal = new Personal();
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(hours, id, personal, salary);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contract other = (Contract) obj;
		return Objects.equals(hours, other.hours) && Objects.equals(id, other.id)
				&& Objects.equals(personal, other.personal) && Objects.equals(salary, other.salary);
	}

	@Override
	public String toString() {
		return "Contract [id=" + id + ", salary=" + salary + ", hours=" + hours + ", personal=" + personal + "]";
	}

	

	

}
