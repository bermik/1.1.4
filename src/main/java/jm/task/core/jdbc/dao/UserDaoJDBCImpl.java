package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.swing.plaf.nimbus.State;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String SAVE_USER = "INSERT INTO UsersTable(name, lastName, age) VALUES(?,?,?)";
    private static final String DELETE_USER = "DELETE FROM UsersTable WHERE id = (?)";
    private static final String GET_ALL_USERS = "SELECT * FROM UsersTable";
    private Util util;
    {
        try {
            util = new Util();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = util.getConnection().createStatement()){
            //if table doesn't exist then create
            if (!util.getConnection()
                    .getMetaData()
                    .getTables(null, null, "UsersTable", new String[] {"TABLE"})
                    .next()) {
                statement.executeUpdate("CREATE TABLE UsersTable (id BIGINT primary key auto_increment, name VARCHAR(40), lastName VARCHAR(40), age TINYINT)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = util.getConnection().createStatement()){
            //if table exists then drop
            if (util.getConnection()
                    .getMetaData()
                    .getTables(null, null, "UsersTable", new String[] {"TABLE"})
                    .next()) {
                statement.executeUpdate("DROP TABLE UsersTable");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = util.getConnection().prepareStatement(SAVE_USER)){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = util.getConnection().prepareStatement(DELETE_USER)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (ResultSet resultSet = util.getConnection().prepareStatement(GET_ALL_USERS).executeQuery()){
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                list.add(user);
            }
        } catch (SQLException e) {
                e.printStackTrace();
            }
        return list;
    }

    public void cleanUsersTable() {
        try (PreparedStatement preparedStatement = util.getConnection().prepareStatement("DELETE FROM UsersTable")){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
