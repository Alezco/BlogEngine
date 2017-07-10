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

    public void register(String email, String username, String password) throws IOException {
        services.create(new User(email, username, password));
        FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + "/user/login.xhtml");
    }

    public void login(String email, String password) throws ServletException, IOException {

        User user = userService.getUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + "/index.xhtml");
        }
        else
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + "/user/login.xhtml");
    }

    public void logout() throws IOException {
        setCurrentUser(null);
        FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + "/index.xhtml");
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
