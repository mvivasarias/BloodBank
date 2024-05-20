package bloodbankJPA;

import java.security.MessageDigest;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import bloodBankIfaces.UserManager;
import bloodBankPOJOs.Role;
import bloodBankPOJOs.User;

public class JPAuserManager implements UserManager {
	private EntityManager em;

	@Override
	public void connect() {

		em = Persistence.createEntityManagerFactory("bloodBank-provider").createEntityManager();

		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys = ON").executeUpdate();
		em.getTransaction().commit();

		if (this.getRoles().isEmpty()) {
			Role personal = new Role("personal");
			Role hospital = new Role("hospital");
			this.newRole(personal);
			this.newRole(hospital);
		}

	}

	@Override
	public List<Role> getRoles() {

		Query q = em.createNativeQuery("SELECT * FROM roles", Role.class);
		List<Role> roles = (List<Role>) q.getResultList();

		return roles;
	}

	@Override
	public void newRole(Role r) {

		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();

	}

	@Override
	public void newUser(User u) {

		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
	}

	@Override
	public void disconnect() {

		em.close();
	}

	@Override
	public Role getRole(Integer id) {

		Query q = em.createNativeQuery("SELECT * FROM roles where id=" + id, Role.class);
		Role r = (Role) q.getSingleResult();

		return r;
	}

	@Override
	public User getUser(String email) {

		Query q = em.createNativeQuery("SELECT * FROM users where email=" + email, User.class);
		User u = (User) q.getSingleResult();

		return u;
	}

	@Override
	public User checkPassword(String email, String pass) {

		User u = null;

		Query q = em.createNativeQuery("SELECT * from users where email =? and password=?", User.class);
		q.setParameter(1, email);

		try {

			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(pass.getBytes());
			byte[] pw = md.digest();

			q.setParameter(2, pw);

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			u = (User) q.getSingleResult();

		} catch (NoResultException e) {
		}

		return u;
	}

	@Override
	public void changePassword(User u, String new_passwd) {
		// TODO Auto-generated method stub

	}
	@Override
	
	public boolean isEmailExisting(String email) {
	    try {
	       
	        Query query = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email");
	        query.setParameter("email", email);
	        
	       
	        Long count = (Long) query.getSingleResult();
	        
	        
	        return count > 0; // If count > 0, email exists
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false; // Return false in case of an exception or error
	    }
	}
}
