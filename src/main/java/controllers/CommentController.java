package controllers;

import dao.CommentDAO;
import models.Article;
import models.Comment;
import models.User;
import services.Services;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Lorris on 10/07/2017.
 */
@SessionScoped
@Named("commentController")
public class CommentController implements Serializable {
    private @Inject
    Services services;

    private @Inject
    CommentDAO commentDAO;

    private FacesContext context = FacesContext.getCurrentInstance();
    private ExternalContext externalContext = context.getExternalContext();
    private HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();


    public void create(String content, User user, Article article) throws IOException {
        services.create(
                new Comment(
                        content,
                        user,
                        article
                )
        );
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }

    public ArrayList<Comment> list(Article article) throws IOException {
        return commentDAO.getCommentsByArticleId(article.getId());
    }
}
