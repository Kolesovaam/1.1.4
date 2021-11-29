package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        User[] users = {new User("Maria", "Kolesova", (byte) 19),
                new User("German", "Glushkov", (byte) 13),
                new User("Egor", "Vaskin", (byte) 22),
                new User("Eva", "Milkanova", (byte) 32)};
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        for (User user : users) {

            userService.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.printf("User с именем – %s добавлен в базу данных\n", user.getName());

        }
        System.out.println("\n" + userService.getAllUsers());

        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.shutdown();

    }
}
