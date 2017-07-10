package controllers;

import models.Article;
import models.Blog;
import services.BlogService;
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
    @Inject private Services services;
    @Inject private BlogService blogService;
    private Article currentArticle;

    public Article getCurrentArticle() {
        return currentArticle;
    }

    public ArrayList<Article> listArticles() {
        return services.getList(Article.class);
    }

    public ArrayList<Article> listActiveArticlesByBlogId(Integer id) {
        return blogService.getActiveArticlesByBlogId(id);
    }

    public void addArticle(String title, String content, Blog blog) {
        Article article = new Article(title, content);
        article.setBlog(blog);
        services.create(article);
        redirectTo("index.xhtml");
    }

    public void updateArticle(String title, String content) {
        currentArticle.setTitle(title);
        currentArticle.setContent(content);
        services.update(currentArticle);
        redirectTo("index.xhtml");
    }

    public void create(Article article) {
        this.currentArticle = article;
        redirectTo("show.xhtml");
    }

    public void edit(Article article) {
        this.currentArticle = article;
        redirectTo("edit.xhtml");
    }

    private void redirectTo(String page) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
