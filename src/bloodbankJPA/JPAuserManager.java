package bloodbankJPA;

import java.security.MessageDigest;
import java.util.List;

import bloodBankIfaces.UserManager;
import bloodBankPOJOs.Role;
import bloodBankPOJOs.User;

public class JPAuserManager implements UserManager {
	// private EntityManager em;

	@Override
	public void connect() {
		// TODO Auto-generated method stub
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub

	}

	@Override
	public void newRole(Role r) {
		// TODO Auto-generated method stub

	}

	@Override
	public void newUser(User u) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role getRole(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User checkPassword(String email, String pass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changePassword(User u, String new_passwd) {
		// TODO Auto-generated method stub

	}
}
