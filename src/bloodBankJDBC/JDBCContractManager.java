package bloodBankJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import bloodBankIfaces.ContractManager;
import bloodBankPOJOs.Contract;

public class JDBCContractManager implements ContractManager {

	private JDBCManager manager;

	public JDBCContractManager(JDBCManager m) {
		this.manager = m;
	}

	@Override
	public void addContract(Contract addContract) {

		try {
			String sql = "INSERT INTO contract (salary, hours) VALUES (?, ?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, addContract.getSalary());
			prep.setInt(2, addContract.getHours());

			// Execute the INSERT statement
			prep.executeUpdate();

			System.out.println("Contract added successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
