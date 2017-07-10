package controllers;

import models.Article;
import services.Services;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

@SessionScoped
@Named("articleController")
public class ArticleController implements Serializable {
    @Inject
    private Services services;

    private Article currentArticle;

    public ArrayList<Article> listArticles() {
        return services.getList(Article.class);
    }

    public void addArticle(String title, String content) {
        Article article = new Article(title, content);
        services.create(article);
    }

    public void create(Article article) {
        this.currentArticle = article;
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("show.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void edit(Article article) {
        this.currentArticle = article;
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("edit.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateArticle( String title, String content) {
        currentArticle.setTitle(title);
        currentArticle.setContent(content);
        services.update(currentArticle);
    }

    public Article getCurrentArticle() {
        return currentArticle;
    }
}
