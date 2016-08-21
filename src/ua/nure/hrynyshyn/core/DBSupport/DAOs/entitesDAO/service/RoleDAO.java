package ua.nure.hrynyshyn.core.DBSupport.DAOs.entitesDAO.service;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.AbstractDAO;
import ua.nure.hrynyshyn.core.entities.service.Role;
import ua.nure.hrynyshyn.core.entities.service.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by GrynyshynRoman on 21.08.2016.
 */
public class RoleDAO extends AbstractDAO<Role> {
    public Role getByLogin(String login) {
        String sql = "SELECT * FROM roles WHERE login=?";
        List<Role> roles = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);
            roles = parseResultSet(statement.executeQuery());
        } catch (SQLException e) {
            log.log(Level.INFO, "Can't find element", e);
        }
        if (roles.size() != 0) {
            return roles.iterator().next();
        } else return null;
    }
    @Override
    protected String getInsertQuery() {
        return "INSERT INTO roles(login, role) VALUES (?,?)";
    }

    @Override
    protected String getByIdQuery() {
        return "SELECT * FROM roles WHERE role_ID=?";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE roles SET login = ?, role = ? WHERE role_ID=?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM roles WHERE role_ID=?";
    }

    @Override
    protected String getGetAllQuery() {
        return "SELECT * FROM roles";
    }

    @Override
    protected List<Role> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Role> roles=new ArrayList<>();
        while (resultSet.next()){
            Role role=new Role();
            role.setRole_ID(resultSet.getInt(1));
            role.setLogin(resultSet.getNString(2));
            role.setRole(resultSet.getString(3));
            roles.add(role);
        }
        return roles;
    }

    @Override
    protected void prepareInsertStatement(PreparedStatement statement, Role object) throws SQLException {
        statement.setString(1,object.getLogin());
        statement.setString(2,object.getRole());
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement statement, Role object) throws SQLException {
        statement.setString(1,object.getLogin());
        statement.setString(2,object.getRole());
        statement.setInt(3,object.getRole_ID());
    }

    @Override
    protected void prepareDeleteStatement(PreparedStatement statement, Role object) throws SQLException {
        statement.setInt(1,object.getRole_ID());
    }

    public RoleDAO(Connection connection) {
        super.connection=connection;
    }
}
