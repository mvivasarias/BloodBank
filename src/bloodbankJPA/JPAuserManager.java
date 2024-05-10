package bloodbankJPA;

import java.security.MessageDigest;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import bloodBankIfaces.UserManager;
import bloodBankPOJOs.Role;
import bloodBankPOJOs.User;

public class JPAuserManager implements UserManager {
	private EntityManager em;

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
			User u = null;
			
			Query q = em.createNativeQuery("SELECT * from users where email =? and password=?", User.class);
			q.setParameter(1, email);
			
			try {
				
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(pass.getBytes());
				byte[] pw = md.digest();
				
				q.setParameter(2, pw);
				
			}catch(Exception e)
			{e.printStackTrace();}
				
			
			try {
				u = (User) q.getSingleResult();
				
			}catch(NoResultException e) {}
			
			return u;
		}
	}

	@Override
	public void changePassword(User u, String new_passwd) {
		// TODO Auto-generated method stub

	}
}
