package bloodBankIfaces;

import java.util.List;

import bloodBankPOJOs.Role;
import bloodBankPOJOs.User;

public interface UserManager {

	public void connect();

	public void disconnect();

	public void newRole(Role r);

	public void newUser(User u);

	public List<Role> getRoles();

	public Role getRole(String name);

	public User getUser(String email);

	public User checkPassword(String email, String pass);

	public void changePassword(User u,  byte[] new_passwd);
	
	public boolean isEmailExisting(String email);
	
	public void deleteUserByEmail(String email);

}
