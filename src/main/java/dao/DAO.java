package dao;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;

@ApplicationScoped
public class DAO {
    @PersistenceContext(unitName = "intrapu")
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
