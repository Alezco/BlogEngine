package dao;

import models.User;

import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;

@SessionScoped
public class UserDAO implements Serializable {
    @PersistenceContext(unitName = "BEPersistenceName")
    private EntityManager em;

    @Transactional
    public User getUserByEmail(String email) {
        return (User) em.createQuery("SELECT u FROM User u WHERE u.email = :email")
                .setParameter("email", email).getResultList().get(0);
    }
}
