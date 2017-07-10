package controllers;

import models.User;
import services.Services;
import services.UserService;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;

@SessionScoped
@Named("userController")
public class UserController implements Serializable {

    private @Inject Services services;
    private @Inject UserService userService;

    FacesContext context = FacesContext.getCurrentInstance();
    ExternalContext externalContext = context.getExternalContext();
    HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

    private User currentUser;

    public void register(String email, String username, String password) {
        services.create(new User(email, username, password));
        redirectTo("/user/login.xhtml");
    }

    public void login(String email, String password) throws ServletException {

        User user = userService.getUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            redirectTo("/index.xhtml");
        }
        else {
            redirectTo("/user/login.xhtml");
        }
    }

    public void logout() {
        setCurrentUser(null);
        redirectTo("/index.xhtml");
    }

    private void redirectTo(String page) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + page);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
