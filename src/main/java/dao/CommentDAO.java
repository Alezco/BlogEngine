package dao;

import models.Article;
import models.Comment;

import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Lorris on 10/07/2017.
 */
@SessionScoped
public class CommentDAO implements Serializable {
    @PersistenceContext(unitName = "BEPersistenceName")
    private EntityManager em;

    @Transactional
    public ArrayList<Comment> getCommentsByArticleId(int id) {
        return (ArrayList<Comment>) em.createQuery("SELECT c FROM Comment c WHERE c.article.id = :id")
                .setParameter("id", id)
                .getResultList();
    }
}
