package bloodBankJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import bloodBankIfaces.ContractManager;
import bloodBankPOJOs.Contract;

public class JDBCContractManager implements ContractManager {

	private JDBCManager manager;

	public JDBCContractManager(JDBCManager m) {
		this.manager = m;
	}

	@Override
	public Contract addContract(Contract addContract) {
		Contract contract = null;
		try {
			String sql = "INSERT INTO contract (salary, hours) VALUES (?, ?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, addContract.getSalary());
			prep.setInt(2, addContract.getHours());

			int affectedRows = prep.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating contract failed, no rows affected.");
			}

			try (ResultSet generatedKeys = prep.getGeneratedKeys()) {

				if (generatedKeys.next()) {
					int generatedId = generatedKeys.getInt(1); // Retrieve the generated ID
					contract = new Contract(generatedId, addContract.getSalary(), addContract.getHours());
				} else {
					throw new SQLException("Creating contract failed, no ID obtained.");
				}
			}

			System.out.println("Contract added successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contract;
	}

	public Contract searchContractById(Integer contractId) {

		Contract contract = null;

		try {
			String sql = "SELECT * FROM contract WHERE id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, contractId);
			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				contract = new Contract(rs.getInt("id"), rs.getInt("salary"), rs.getInt("hours"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contract;
	}

}
