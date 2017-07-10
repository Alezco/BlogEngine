package controllers;

import dao.UserDAO;
import models.User;
import services.Services;

import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Lorris on 10/07/2017.
 */

@RequestScoped
@Named("userController")
public class UserController {

    private @Inject
    Services services;
    private @Inject
    UserDAO userDAO;

    private FacesContext context = FacesContext.getCurrentInstance();
    private ExternalContext externalContext = context.getExternalContext();
    private HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
    private HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

    public void register(String email, String username, String password) {
        services.create(new User(email, username, password));
    }

    public void login(String email, String password) throws ServletException, IOException {
        User user = userDAO.getUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            Cookie cookieStored = getCookie(request);
            if (cookieStored == null) {
                Cookie cookie = new Cookie("email", user.getEmail());
                cookie.setMaxAge(24*60*60);
                response.addCookie(cookie);
            }

            response.sendRedirect("/blogengine");
        }
        else {
            response.sendRedirect("login.xhtml");
        }
    }

    public void logout() throws IOException {
        Cookie cookieStored = getCookie(request);
        if (cookieStored != null) {
            cookieStored.setMaxAge(0);
            cookieStored.setValue("");
            response.addCookie(cookieStored);
            response.sendRedirect("");
        }
    }

    public boolean isLogged() {
        return getCookie(request) != null;
    }

    private Cookie getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("email")) {
                    return cookie;
                }
            }
        }
        return null;
    }
}
