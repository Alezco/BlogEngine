package controllers;

import models.Article;
import services.Services;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;

@RequestScoped
@Named("articleController")
public class ArticleController {
    @Inject
    private Services services;

    public ArrayList<Article> listArticles() {
        return services.getList(Article.class);
    }

    public void addArticle(String title, String content) {
        Article article = new Article(title, content);
        services.create(article);
    }
}
