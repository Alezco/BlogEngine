package services;

import dao.UserDAO;
import models.User;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;

@SessionScoped
public class UserService implements Serializable {
    private @Inject
    UserDAO userDAO;

    @Transactional
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }
}
