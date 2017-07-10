package controllers;

import dao.UserDAO;
import models.User;
import services.Services;

import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SessionScoped
@Named("userController")
public class UserController {

    private @Inject
    Services services;
    private @Inject
    UserDAO userDAO;

    private User currentUser;

    public void register(String email, String username, String password) {
        services.create(new User(email, username, password));
    }

    public void login(String email, String password) throws ServletException, IOException {

        User user = userDAO.getUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            response.sendRedirect("index.xhtml");
        }
        else {
            response.sendRedirect("login.xhtml");
        }
    }

    public void logout() throws IOException {
        setCurrentUser(null);
    }

    public boolean isLogged() {
        return getCurrentUser() != null;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
}
