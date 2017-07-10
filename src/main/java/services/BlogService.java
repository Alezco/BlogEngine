package services;

import dao.BlogDAO;
import models.Article;
import models.Blog;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;

public class BlogService {
    private @Inject
    BlogDAO blogDAO;

    @Transactional
    public ArrayList<Article> getArticlesByBlogId(int id) {
        return blogDAO.getArticlesByBlogId(id);
    }

    @Transactional
    public ArrayList<Article> getActiveArticlesByBlogId(int id) {
        return blogDAO.getActiveArticlesByBlogId(id);
    }

    @Transactional
    public ArrayList<Blog> getActiveBlogs() {
        return blogDAO.getActiveBlogs();
    }
}
