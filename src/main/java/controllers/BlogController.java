package controllers;

import models.Blog;
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

    public void add(String name) {
        Blog blog = new Blog();
        blog.setName(name);
        blog.setArchived(false);
        blog.setCreationDate(new Timestamp(System.currentTimeMillis()));
        //blog.setOwner();
        System.out.println(name);

        services.create(blog);
    }

    public void show(Integer id) throws IOException {
        current = (Blog)services.getById(Blog.class, id);

        FacesContext.getCurrentInstance().getExternalContext().redirect("show.xhtml");
    }
}
