package controllers;

import models.User;
import services.Services;
import services.UserService;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SessionScoped
@Named("userController")
public class UserController implements Serializable {
    private @Inject Services services;
    private @Inject UserService userService;
    private final FacesContext context = FacesContext.getCurrentInstance();
    private final ExternalContext externalContext = context.getExternalContext();
    private final HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
    private final HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
    private User currentUser;
    private boolean hasLoginError = false;
    private boolean emailUsed = false;
    private boolean usernameUsed = false;

    public void register(final String email, final String username, final String password) throws IOException {
        User user = userService.getUserByEmail(email);
        User user2 = userService.getUserByUsername(username);

        if (user != null)
            setEmailUsed(true);
        if (user2 != null)
            setUsernameUsed(true);
        if (user == null && user2 == null) {
            setEmailUsed(false);
            setUsernameUsed(false);
            services.create(new User(email, username, hashPassword(password)));
            redirectTo("/user/login.xhtml");
        }
        else
            redirectTo("/user/register.xhtml");
    }

    public void login(final String email, final String password) throws ServletException, IOException {
        User user = userService.getUserByEmail(email);
        if (user != null && user.getPassword().equals(hashPassword(password))) {
            currentUser = user;
            hasLoginError = false;
            redirectTo("/index.xhtml");
        }
        else {
            hasLoginError = true;
            redirectTo("/user/login.xhtml");
        }
    }

    public void logout() {
        setCurrentUser(null);
        redirectTo("/index.xhtml");
    }

    private void redirectTo(final String page) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String hashPassword(final String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            return sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            redirectTo("/index.xhtml");
            return "";
        }
    }

    public void setRegisterError(final boolean bool) {
        setUsernameUsed(bool);
        setEmailUsed(bool);
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

    public boolean isHasLoginError() {
        return hasLoginError;
    }

    public void setHasLoginError(final boolean hasLoginError) {
        this.hasLoginError = hasLoginError;
    }

    public boolean isEmailUsed() {
        return emailUsed;
    }

    public void setEmailUsed(final boolean emailUsed) {
        this.emailUsed = emailUsed;
    }

    public boolean isUsernameUsed() {
        return usernameUsed;
    }

    public void setUsernameUsed(final boolean usernameUsed) {
        this.usernameUsed = usernameUsed;
    }
}
