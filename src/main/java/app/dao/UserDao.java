package app.dao;

import app.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    void add(User user);
    void removeUser(Long id);
    void changeUser(User user);
    User getUser(Long id);
    List<User> getUsersList();
}