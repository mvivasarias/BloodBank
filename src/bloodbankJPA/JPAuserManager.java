package bloodbankJPA;

import java.security.MessageDigest;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
	public Role getRole(String name) {
		Query q = em.createNativeQuery("SELECT * FROM roles WHERE name LIKE ?", Role.class);
		q.setParameter(1, name);
		Role r = (Role) q.getSingleResult();
		return r;
	}

	@Override
	public User getUser(String email) {

		Query q = em.createNativeQuery("SELECT * FROM users where email= ?" + email, User.class);
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
	public void changePassword(User u, byte[] new_passwd) {
		EntityTransaction transaction = null;
		try {

			// Retrieve the user from the database using email
			User userToUpdate = em.find(User.class, u.getEmail());

			// Check if the current password matches
			if (userToUpdate.getPassword().equals(u.getPassword())) {

				// Update the password with the new one
				userToUpdate.setPassword(new_passwd);
				em.merge(userToUpdate);
				transaction.commit();

				System.out.println("Password updated successfully.");
			} else {
				System.out.println("Incorrect current password. Password not updated.");
			}
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public boolean isEmailExisting(String email) {
		try {

			Query query = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email");
			query.setParameter("email", email);

			Long count = (Long) query.getSingleResult();

			return count > 0; // If count > 0, email exists if not email DOES NOT EXIST
		} catch (Exception e) {
			e.printStackTrace();
			return false; // Return false in case of an exception or error
		}
	}

	@Override
	public void deleteUserByEmail(String email) {
		try {
			em.getTransaction().begin();
			Query query = em.createNativeQuery("Select * FROM users WHERE email=?", User.class);
			query.setParameter(1, email);
			User user = (User) query.getSingleResult();

			em.remove(user);
			em.getTransaction().commit();
			
			 System.out.println("User deleted successfully");

		} catch (Exception e) {
			e.printStackTrace();
			
			 System.out.println("User failed to be deleted");

		}

	}
}
