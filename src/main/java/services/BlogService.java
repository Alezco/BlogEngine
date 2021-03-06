package services;

import dao.BlogDAO;
import models.Article;
import models.Blog;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;

@SessionScoped
public class BlogService implements Serializable {
    private @Inject
    BlogDAO blogDAO;

    @Transactional
    public ArrayList<Article> getArticlesByBlogId(final int id) {
        return blogDAO.getArticlesByBlogId(id);
    }

    @Transactional
    public ArrayList<Article> getActiveArticlesByBlogId(final int id) {
        return blogDAO.getActiveArticlesByBlogId(id);
    }

    @Transactional
    public ArrayList<Article> getInactiveArticlesByBlogId(final int id) {
        return blogDAO.getInactiveArticlesByBlogId(id);
    }

    @Transactional
    public ArrayList<Blog> getActiveBlogs() {
        return blogDAO.getActiveBlogs();
    }

    @Transactional
    public ArrayList<Blog> getInactiveBlogs() {
        return blogDAO.getInactiveBlogs();
    }
}
