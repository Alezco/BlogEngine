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
        System.out.println("================");
        System.out.println(services.getList(Article.class));
        System.out.println("================");
        return services.getList(Article.class);
    }
}
