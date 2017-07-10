package dao;

import models.Article;
import models.Blog;

import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;

@SessionScoped
public class BlogDAO implements Serializable {
    @PersistenceContext(unitName = "BEPersistenceName")
    private EntityManager em;

    @Transactional
    public ArrayList<Article> getArticlesByBlogId(int id) {
        return (ArrayList<Article>) em.createQuery("SELECT a FROM Article a WHERE a.blog.id = :id")
                .setParameter("id", id)
                .getResultList();
    }

    @Transactional
    public ArrayList<Article> getActiveArticlesByBlogId(int id) {
        return (ArrayList<Article>) em.createQuery("SELECT a FROM Article a WHERE a.blog.id = :id AND a.archived = false")
                .setParameter("id", id)
                .getResultList();
    }

    @Transactional
    public ArrayList<Blog> getActiveBlogs() {
        return (ArrayList<Blog>) em.createQuery("SELECT b FROM Blog b WHERE b.archived = false")
                .getResultList();
    }
}
