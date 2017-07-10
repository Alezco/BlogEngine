package services;

import dao.UserDAO;
import models.User;

import javax.inject.Inject;
import javax.transaction.Transactional;

public class UserService {
    private @Inject
    UserDAO userDAO;

    @Transactional
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }
}
