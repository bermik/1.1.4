package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private final String URL = "jdbc:mysql://localhost:3306/mdbtest";
    private final String USER = "root";
    private final String PASSWORD = "root";
    private final Connection connection;

    public Util() throws SQLException{
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public Connection getConnection() {
        return connection;
    }
    public Statement getStatement() throws SQLException{
        return connection.createStatement();
    }
}
