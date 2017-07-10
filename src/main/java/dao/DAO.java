package dao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;

@SessionScoped
public class DAO implements Serializable {
    @PersistenceContext(unitName = "BEPersistenceName")
    private EntityManager em;

    public <T> ArrayList<T> getList(Class tClass) {
        return (ArrayList<T>) em.createQuery("SELECT x FROM " + tClass.getSimpleName() + " x").getResultList();
    }

    @Transactional
    public <T> Object getById(Class<T> tClass, int id) {
        return em.find(tClass, id);
    }

    @Transactional
    public <T> void create(T object) {
        em.persist(object);
    }

    @Transactional
    public <T> void update(T object) {
        em.merge(object);
    }

    @Transactional
    public <T> void delete(T object) {
        em.remove(object);
    }
}
