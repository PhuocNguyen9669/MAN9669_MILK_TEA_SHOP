package menu.services;

import menu.model.Role;
import menu.model.User;
import menu.utils.CSVUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class UserService implements IUserService {
    public static String PATH = "data/users.csv";
    private static UserService instance;

    private UserService() {

    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    @Override
    public List<User> findALl() {
        List<User> users = new ArrayList<>();
        List<String> records = CSVUtils.read(PATH);
        for (String record : records) {
            users.add(User.parseUser(record));
        }
        return users;
    }

    @Override
    public User adminLogin(String username, String password) {
        List<User> users = findALl();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)
                    && user.getRole().equals(Role.ADMIN)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void add(User newUser) {
        newUser.setCreateAT(Instant.now());
        List<User> users = findALl();
        users.add(newUser);
        CSVUtils.write(PATH, users);
    }

    @Override
    public void update(User newUser) {
        List<User> users = findALl();
        for (User user : users) {
            if (user.getId() == newUser.getId()) {
                String name = newUser.getFullName();
                if (name != null && !name.isEmpty())
                    user.setFullName(name);
                String phone = newUser.getPhone();
                if (phone != null && !phone.isEmpty())
                    user.setPhone(newUser.getPhone());
                String address = newUser.getAddress();
                if (address != null && !address.isEmpty())
                    user.setAddress(newUser.getAddress());
                user.setUpdatedAT(Instant.now());
                CSVUtils.write(PATH, users);
                break;
            }
        }
    }
    @Override
    public boolean existsById(int id) {
        return findById(id) != null;
    }

    @Override
    public boolean existsByPhone(String phone) {
        List<User> users = findALl();
        for (User user : users) {
            if (user.getPhone().equals(phone))
                return true;
        }
        return false;
    }

    @Override
    public boolean existsByUsername(String userName) {
        List<User> users = findALl();
        for (User user : users) {
            if (user.getUsername().equals(userName))
                return true;
        }
        return false;
    }

    @Override
    public void deleteById(int id) {
        List<User> users = findALl();

        users.removeIf(new Predicate<User>() {
            @Override
            public boolean test(User user) {
                return user.getId() == id;
            }
        });
        CSVUtils.write(PATH, users);
    }


    @Override
    public User findById(int id) {
        List<User> users = findALl();
        for (User user : users) {
            if (user.getId() == id)
                return user;
        }
        return null;
    }
}
