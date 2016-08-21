package ua.nure.hrynyshyn.core.DBSupport.DAOs.entitesDAO.service;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.AbstractDAO;
import ua.nure.hrynyshyn.core.entities.service.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;

/**
 * Created by GrynyshynRoman on 18.08.2016.
 */
public class UserDAO extends AbstractDAO<User> {
    public User getByLogin(String login) {
        String sql = "SELECT * FROM users WHERE login=?";
        List<User> users = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);
            users = parseResultSet(statement.executeQuery());
        } catch (SQLException e) {
            log.log(Level.INFO, "Can't find element", e);
        }
        if (users.size() != 0) {
            return users.iterator().next();
        } else return null;
    }

    public List<String> getLogins(){
        String slq="SELECT  login FROM users";
        List<String> logins=new ArrayList<>();
        try (PreparedStatement statement=connection.prepareStatement(slq)){
            ResultSet rs=statement.executeQuery();
            while (rs.next()){
                logins.add(rs.getString(1));
            }
        }catch (SQLException e){
            log.log(Level.INFO,"Problems during processing with database", e);
        }
        return logins;
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO users(login, firstName, lastName, password) VALUES (?,?,?,?)";
    }

    @Override
    protected String getByIdQuery() {
        return "SELECT * FROM users WHERE user_ID=?";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE RAILWAY.USERS SET login=? firstName=?,lastName=?, password=? WHERE user_ID=?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM RAILWAY.USERS WHERE user_ID=?";
    }

    @Override
    protected String getGetAllQuery() {
        return "SELECT * FROM RAILWAY.USERS;";
    }

    @Override
    protected List<User> parseResultSet(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setUser_ID(resultSet.getInt(1));
            user.setLogin(resultSet.getString(2));
            user.setFirstName(resultSet.getString(3));
            user.setLastName(resultSet.getString(4));
            user.setPassword(resultSet.getString(5));
            users.add(user);
        }
        return users;
    }

    @Override
    protected void prepareInsertStatement(PreparedStatement statement, User object) throws SQLException {
        statement.setString(1, object.getLogin());
        statement.setString(2, object.getFirstName());
        statement.setString(3, object.getLastName());
        statement.setString(4, object.getPassword());
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement statement, User object) throws SQLException {
        statement.setString(1, object.getLogin());
        statement.setString(2, object.getFirstName());
        statement.setString(3, object.getLastName());
        statement.setString(4, object.getPassword());
        statement.setInt(5, object.getUser_ID());
    }

    @Override
    protected void prepareDeleteStatement(PreparedStatement statement, User object) throws SQLException {
        statement.setInt(1, object.getUser_ID());
    }

    public UserDAO(Connection connection) {
        super.connection = connection;
    }
}
