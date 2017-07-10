package dao;

import models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Created by Lorris on 10/07/2017.
 */
public class UserDAO {
    @PersistenceContext(unitName = "BEPersistenceName")
    private EntityManager em;

    @Transactional
    public User getUserByEmail(String email) {
        return (User) em.createQuery("SELECT u FROM User u WHERE u.email = :email")
                .setParameter("email", email).getResultList().get(0);
    }
}
