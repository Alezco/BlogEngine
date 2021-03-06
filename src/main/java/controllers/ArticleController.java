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
  
    public ArrayList<Article> listActiveArticlesByBlogId(final Integer id) {
        return blogService.getActiveArticlesByBlogId(id);
    }

    public ArrayList<Article> listInactiveArticlesByBlogId(final Integer id) {
        return blogService.getInactiveArticlesByBlogId(id);
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

    public void show(Article article) {
        this.currentArticle = article;
        redirectTo("show.xhtml");
    }

    public void edit(Article article) {
        this.currentArticle = article;
        redirectTo("edit.xhtml");
    }

    public void archive(Article article) throws IOException {
        article.setArchived(true);
        services.update(article);
        redirectTo("index.xhtml");
    }

    public void restore(Article article) throws IOException {
        article.setArchived(false);
        services.update(article);
        redirectTo("restoration.xhtml");
    }

    public int getAuthorId(Article article) {
        return article.getBlog().getOwner().getId();
    }

    private void redirectTo(String page) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
