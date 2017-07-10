package controllers;

import dao.UserDAO;
import models.User;
import services.Services;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.Serializable;

@SessionScoped
@Named("userController")
public class UserController implements Serializable {

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
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        }
        else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        }
    }

    public void logout() throws IOException {
        setCurrentUser(null);
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
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
