package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan","Ivanov", (byte) 24);
        userService.saveUser("Vasya","Vasilev", (byte) 43);
        userService.saveUser("Alex","Alexeev", (byte) 12);
        userService.saveUser("Steve","Smith", (byte) 34);
        List<User> list = userService.getAllUsers();
        list.forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
