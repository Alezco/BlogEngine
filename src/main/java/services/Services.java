package services;

import dao.DAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;

@ApplicationScoped
public class Services {
    private @Inject DAO dao;

    public <T> ArrayList<T> getList(Class tClass) {
        return dao.getList(tClass);
    }

    public <T> Object getById(Class<T> tClass, int id) {
        return dao.getById(tClass, id);
    }

    public <T> void create(T object) {
        dao.create(object);
    }

    public <T> void update(T object) {
        dao.update(object);
    }

    public <T> void delete(T object) {
        dao.delete(object);
    }
}
