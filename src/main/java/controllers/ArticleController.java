package controllers;

import models.Article;
import services.Services;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
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

    public void setCurrentArticle(Article article) {
        this.currentArticle = article;
    }
}
