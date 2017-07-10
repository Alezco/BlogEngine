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
    private User currentUser = new UserController().getCurrentUser();
    private Article currentArticle = new ArticleController().getCurrentArticle();


    public void create(String content) throws IOException {
        services.create(
                new Comment(
                        content,
                        currentUser,
                        currentArticle
                )
        );
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }

    public ArrayList<Comment> list() throws IOException {
        return commentDAO.getCommentsByArticleId(currentArticle.getId());
    }
}
