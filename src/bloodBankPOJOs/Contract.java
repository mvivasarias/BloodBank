package bloodBankPOJOs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Contract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7520806989904275224L;
	
	private Integer id;
	private Integer salary;
	private Integer hours;
	private List<Personal> personals;   
	
	public Contract() {
		super();
		this.personals = new ArrayList<Personal>();
	}

	public Contract(Integer salary, Integer hours, List<Personal> personals) {
		super();
		this.salary = salary;
		this.hours = hours;
		this.personals = new ArrayList<Personal>();
	}

	public Contract(Integer id, Integer salary, Integer hours, List<Personal> personals) {
		super();
		this.id = id;
		this.salary = salary;
		this.hours = hours;
		this.personals = personals;
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

	public List<Personal> getPersonals() {
		return personals;
	}

	public void setPersonals(List<Personal> personals) {
		this.personals = personals;
	}

	@Override
	public int hashCode() {
		return Objects.hash(hours, id, personals, salary);
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
				&& Objects.equals(personals, other.personals) && Objects.equals(salary, other.salary);
	}

	@Override
	public String toString() {
		return "Contract [id=" + id + ", salary=" + salary + ", hours=" + hours + ", typeofwork=" +  "]";
	}

}
