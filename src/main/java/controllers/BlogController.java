package controllers;

import models.Blog;
import services.Services;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by Lorris on 10/07/2017.
 */

@RequestScoped
@Named("blogController")
public class BlogController {
    @Inject private Services services;

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
}
