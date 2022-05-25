package menu.services;

import menu.model.User;

import java.util.List;

public interface IUserService {
    List<User> findALl();
    User adminLogin(String username, String password );
    void add(User newUser);
    void update(User newUser);
    boolean existsById(int id);
    boolean existsByPhone(String phone);
    boolean existsByUsername(String userName);


    void deleteById(int id);

    User findById(int id);

}
