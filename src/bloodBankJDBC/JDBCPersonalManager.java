package bloodBankJDBC;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bloodBankIfaces.PersonalManager;
import bloodBankPOJOs.Contract;
import bloodBankPOJOs.Personal;

public class JDBCPersonalManager implements PersonalManager {
	private JDBCManager manager;
	private JDBCContractManager contractManager;

	public JDBCPersonalManager(JDBCManager m) {
		this.manager = m;
	}

	public JDBCPersonalManager(JDBCManager m, JDBCContractManager contractManager) {
		this.manager = m;
		this.contractManager = contractManager;
	}

	@Override
	public void addPersonal(Personal personalToADD) {

		try {
			String sql = "INSERT INTO personal (name, surname, email, foto, contract_id) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);

			prep.setString(1, personalToADD.getName());
			prep.setString(2, personalToADD.getSurname());
			prep.setString(3, personalToADD.getEmail());
			prep.setBytes(4, personalToADD.getPhoto());
			prep.setInt(5, personalToADD.getContract().getId());
			prep.executeUpdate();

			prep.close();

			System.out.println("Nurse added successfully to the blood bank database");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Personal searchPersonalByEmail(String emailSearch) {

		Personal person = null;

		try {

			String sql = "SELECT * FROM personal WHERE email = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, emailSearch);

			ResultSet rs = prep.executeQuery();

			if (rs.next()) {

				Integer person_id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String email = rs.getString("email");
				byte[] photo = rs.getBytes("foto");
				Integer contract_id = rs.getInt("contract_id");

				Contract contract = contractManager.searchContractById(contract_id);

				person = new Personal(person_id, name, surname, email, contract, photo);

				System.out.println("You are successfuly registered as personal in the blood bank database with email: "+emailSearch+"contiue with your action ");
			} else {
				System.out.println("You are not in the blood bank database, please register first!");
			}
			rs.close();
			prep.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return person;
	}

	@Override
	public void modifyPersonal(Personal personalToModify, String newName, String newSurname, String newEmail,
			String newPhoto) {
		// not modifying neither the id nor the contract
		System.out.println("modifying...");
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

				personalToModify.setPhoto(newPhoto.getBytes());
			}

			String sql = "UPDATE personal SET name = ?, surname = ?, email = ?, foto = ? WHERE id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);

			prep.setString(1, personalToModify.getName());
			prep.setString(2, personalToModify.getSurname());
			prep.setString(3, personalToModify.getEmail());
			prep.setBytes(4, personalToModify.getPhoto());

			int rowsUpdated = prep.executeUpdate();
			prep.close();
			if (rowsUpdated > 0) {
				System.out.println("Nurse modified successfully.");
			} else {
				System.out.println("ERROR updating nurse.");
			}

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

			int rowsAffected = prep.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Nurse with id: " + id + " deleted successfully.");
			} else {
				System.out.println("No personal with id: " + id + " found.");
			}

			prep.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isPersonalTableNotEmpty() { // check if there is some personal that can do a donation
		boolean isNotEmpty = false;

		String sql = "SELECT COUNT(*) FROM personal";
		try {
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				isNotEmpty = count > 0; // if count is higher than 0 it returns TRUE
			}
			rs.close();
			prep.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isNotEmpty;
	}

	@Override
	public Personal searchPersonalByID(Integer id) {

		Personal personal = null;
		try {
			String sql = "SELECT * FROM personal WHERE id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String email = rs.getString("email");
				Blob photoBlob = rs.getBlob("foto");
				byte[] fotoBytes = null;
				if (photoBlob != null) {
					fotoBytes = photoBlob.getBytes(1, (int) photoBlob.length());
				}

				Contract contract = contractManager.searchContractById(rs.getInt("contract_id"));
				personal = new Personal(id, name, surname, email, contract, fotoBytes);
			}

			rs.close();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personal;
	}

	@Override
	public List<Personal> listPersonal() {
		List<Personal> personal = new ArrayList<>();
		try {
			String sql = "SELECT * FROM personal ORDER BY id";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String email = rs.getString("email");
				byte[] photo = rs.getBytes("foto");
				int contractId = rs.getInt("contract_id");
				Contract contract = contractManager.searchContractById(contractId);

				Personal nurse = new Personal(id, name, surname, email, contract, photo);
				personal.add(nurse);
			}

			rs.close();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personal;
	}
}
