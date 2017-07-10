package controllers;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOError;

/**
 * Created by Lorris on 10/07/2017.
 */

@RequestScoped
@Named("user")
public class UserController {

    public void signUp(String email, String username, String password) {
        System.out.println(email);
        System.out.println(username);
        System.out.println(password);
    }
}
