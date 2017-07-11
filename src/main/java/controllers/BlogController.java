package controllers;

import models.Blog;
import models.User;
import services.BlogService;
import services.Services;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

@SessionScoped
@Named("blogController")
public class BlogController implements Serializable {
    @Inject private Services services;
    @Inject private BlogService blogService;
    private Blog current;

    public Blog getCurrent() {
        return current;
    }

    public void setCurrent(Blog current) {
        this.current = current;
    }

    public ArrayList<Blog> list() {
        return services.getList(Blog.class);
    }

    public ArrayList<Blog> activeList() {
        return blogService.getActiveBlogs();
    }

    public ArrayList<Blog> inactiveList() {
        return blogService.getInactiveBlogs();
    }

    public void add(String name, User user) {
        Blog blog = new Blog();
        blog.setName(name);
        blog.setArchived(false);
        blog.setCreationDate(new Timestamp(System.currentTimeMillis()));
        blog.setOwner(user);
        services.create(blog);
        redirectTo("index.xhtml");
    }

    public void show(final Integer id) {
        current = (Blog)services.getById(Blog.class, id);
        redirectTo("article/index.xhtml");
    }

    public void archive(Blog blog) {
        blog.setArchived(true);
        services.update(blog);
        redirectTo("index.xhtml");
    }

    public void restore(Blog blog) {
        blog.setArchived(false);
        services.update(blog);
        redirectTo("restoration.xhtml");
    }

    private void redirectTo(String page) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
