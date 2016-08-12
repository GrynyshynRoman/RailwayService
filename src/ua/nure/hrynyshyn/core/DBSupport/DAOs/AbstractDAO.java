package ua.nure.hrynyshyn.core.DBSupport.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by GrynyshynRoman on 18.07.2016.
 */
public abstract class AbstractDAO<T> implements GenericDAO<T> {
    private static Logger log = Logger.getLogger(AbstractDAO.class.getName());

    protected Connection connection;

    protected abstract String getInsertQuery();

    protected abstract String getByIdQuery();

    protected abstract String getUpdateQuery();

    protected abstract String getDeleteQuery();

    protected abstract String getGetAllQuery();

    protected abstract List<T> parseResultSet(ResultSet resultSet) throws SQLException;

    protected abstract void prepareInsertStatement(PreparedStatement statement, T object) throws SQLException;

    protected abstract void prepareUpdateStatement(PreparedStatement statement, T object) throws SQLException;

    protected abstract void prepareDeleteStatement(PreparedStatement statement, T object) throws SQLException;



    @Override
    public boolean insert(T object) {
        String sql = getInsertQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareInsertStatement(statement, object);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
        return true;
    }

    @Override
    public T read(int id) {
        String sql = getByIdQuery();
        List<T> objects = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            objects = parseResultSet(statement.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objects.iterator().next();
    }

    @Override
    public boolean update(T object) {
        String sql = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareUpdateStatement(statement, object);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(T object) {
        String sql = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareDeleteStatement(statement, object);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
        return true;
    }

    @Override
    public List<T> getAll() {
        String sql = getGetAllQuery();
        List<T> objects = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            objects = parseResultSet(statement.executeQuery());
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Can't execute query", e);
        }
        return objects;
    }
}
