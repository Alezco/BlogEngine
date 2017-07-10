package dao;

import models.Article;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;

public class BlogDAO {
    @PersistenceContext(unitName = "BEPersistenceName")
    private EntityManager em;

    @Transactional
    public ArrayList<Article> getArticlesByBlogId(int id) {
        return (ArrayList<Article>) em.createQuery("SELECT a FROM Article a WHERE a.blog.id = :id")
                .setParameter("id", id)
                .getResultList();
    }
}
