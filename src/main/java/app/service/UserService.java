package app.service;

import app.model.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    void add(User user);
    void removeUser(Long id);
    void changeUser(User user);
    User getUser(Long id);
    List<User> getUsersList();
}

