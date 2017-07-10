package webservices;

import dao.BlogDAO;
import dao.DAO;
import models.Article;
import models.Blog;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.ArrayList;

@ApplicationScoped
@WebService
@Path("/blog")
@Produces("application/json; charset=UTF-8")
public class BlogService {
    private @Inject DAO dao;
    private @Inject BlogDAO blogDAO;

    @GET
    @Path("/insert")
    @Transactional
    public String insertDefault (@Context final HttpServletRequest request) {
        Blog blog = new Blog();
        blog.setName("Test Blog");
        dao.create(blog);
        return "ok";
    }

    @GET
    @Path("/")
    @Transactional
    public ArrayList<Blog> list(@Context final HttpServletRequest request) {
        return dao.getList(Blog.class);
    }

    @GET
    @Transactional
    @Path("/{id}/articles")
    public ArrayList<Article> get(@PathParam("id") final int id) {
        return blogDAO.getArticlesByBlogId(id);
    }
}
