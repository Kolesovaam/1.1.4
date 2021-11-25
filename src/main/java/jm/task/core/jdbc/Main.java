package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    private final static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        userService.createUsersTable();

        User user1 = new User("Maria", "Kolesova", (byte) 19);
        User user2 = new User("Nikita", "Kotov", (byte) 23);
        User user3 = new User("Sonya","Melnikova",(byte) 13);
        User user4 = new User("Ivan", "Ivanov", (byte) 32);

        userService.removeUserById(3);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();






    }
}
