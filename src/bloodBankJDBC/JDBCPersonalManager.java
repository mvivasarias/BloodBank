package bloodBankJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import bloodBankIfaces.PersonalManager;
import bloodBankPOJOs.Contract;
import bloodBankPOJOs.Personal;

public class JDBCPersonalManager implements PersonalManager {
	private JDBCManager manager;
	private JDBCContractManager contractManager;

	public JDBCPersonalManager(JDBCManager m) {
		this.manager = m;
	}

	@Override
	public void addPersonal(Personal personalToADD) {

		try {
			String sql = "INSERT INTO personal (name, surname, email, foto, contract_id) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);

			prep.setString(1, personalToADD.getName());
			prep.setString(2, personalToADD.getSurname());
			prep.setString(3, personalToADD.getEmail());
			prep.setBytes(4, personalToADD.getFoto());
			prep.setInt(5, personalToADD.getContract().getId());

			prep.executeUpdate();

			System.out.println("Nurse added successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Personal searchPersonalByEmail(String emailSearch) { // SHOULD I ADD CO

		Personal person = null;

		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM personal WHERE email = ?";

			ResultSet rs = stmt.executeQuery(sql);

			Integer person_id = rs.getInt("id");
			String name = rs.getString("name");
			String surname1 = rs.getString("surname");
			String email = rs.getString("email");
			byte[] photo = rs.getBytes("photo");
			Integer contract_id = rs.getInt("contract_id");

			Contract contract = contractManager.searchContractById(contract_id);

			person = new Personal(person_id, name, surname1, email, contract, photo);
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return person;
	}

	@Override
	public void modifyPersonal(Personal personalToModify, String newName, String newSurname, String newEmail,
			String newPhoto) {
		// not modifying neither the id nor the contract
		try {

			if (newName != null && !newName.isEmpty()) {
				personalToModify.setName(newName);
			}
			if (newSurname != null && !newSurname.isEmpty()) {
				personalToModify.setSurname(newSurname);
			}
			if (newEmail != null && !newEmail.isEmpty()) {
				personalToModify.setEmail(newEmail);
			}
			if (newPhoto != null && !newPhoto.isEmpty()) { // CONVERT TO BYTES IF PHOTO IS INTRODUCED IN A STRING FORMAT

				personalToModify.setFoto(newPhoto.getBytes());
			}

			String sql = "UPDATE personal SET name = ?, surname = ?, email = ?, foto = ? WHERE id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);

			prep.setString(1, personalToModify.getName());
			prep.setString(2, personalToModify.getSurname());
			prep.setString(3, personalToModify.getEmail());
			prep.setBytes(4, personalToModify.getFoto());

			int rowsUpdated = prep.executeUpdate();

			if (rowsUpdated > 0) {
				System.out.println("Nurse modified successfully.");
			} else {
				System.out.println("ERROR updating nurse.");
			}

			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deletePersonalByID(Integer id) {
		try {
			String sql = "DELETE FROM personal WHERE id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);

			prep.setInt(1, id);

			prep.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isPersonalTableNotEmpty() { //check if there is some personal that can do a donation
		boolean isNotEmpty = false;

		String sql = "SELECT COUNT(*) FROM personal";
		try {
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			ResultSet rs = prep.executeQuery(sql);
			if (rs.next()) {
				int count = rs.getInt(1);
				isNotEmpty = count > 0; //if count is higher than 0 it returns TRUE
			}
			rs.close();
			prep.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isNotEmpty;
	}
}
