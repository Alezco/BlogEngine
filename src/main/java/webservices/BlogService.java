package webservices;

import dao.DAO;
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
    private @Inject DAO blogDAO;

    /*@GET
    @Path("/insert")
    @Transactional
    public String insertDefault (@Context final HttpServletRequest request) {
        User user = new User();
        user.setEmail("moussanji@gmail.com");
        user.setLogin("oufkir_m");
        userDAO.create(user);
        return "ok";
    }*/

    @GET
    @Path("/")
    @Transactional
    public ArrayList<Blog> list(@Context final HttpServletRequest request) {
        return blogDAO.getList(Blog.class);
    }

    /*@GET
    @Transactional
    @Path("/{id}")
    public User get(@PathParam("id") final int id) {
        return userDAO.read(id, User.class);
    }

    @POST
    @Transactional
    @Path("/")
    public User insert(@FormParam("login") final String login,
                       @FormParam("email") final String email) {
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        return userDAO.create(user);
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public User update(@PathParam("id") final int id,
                       @FormParam("login") final String login,
                       @FormParam("email") final String email) {
        User user = userDAO.read(id, User.class);
        user.setLogin(login);
        user.setEmail(email);
        return userDAO.update(user);
    }

    @DELETE
    @Transactional
    @Path("/delete/{id}")
    public void delete(@PathParam("id") final int id) {
        User user = userDAO.read(id, User.class);
        userDAO.delete(user);
    }*/
}
