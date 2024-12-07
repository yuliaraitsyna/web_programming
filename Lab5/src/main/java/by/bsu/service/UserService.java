package by.bsu.service;

import by.bsu.dao.UserDao;
import by.bsu.entity.User;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class UserService {

    @EJB
    private UserDao userDao = new UserDao();

    public User authenticate(String username, String password) {
        User user = userDao.getUserByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

}
