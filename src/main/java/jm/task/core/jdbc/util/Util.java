package jm.task.core.jdbc.util;

import java.sql.*;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/mdbtest";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;

    public Util() {
    }

    public static Connection getConnection() throws SQLException{
        if (connection == null) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);
        }
        return connection;
    }
}
