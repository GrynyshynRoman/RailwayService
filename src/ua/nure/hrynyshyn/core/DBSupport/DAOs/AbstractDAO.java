package ua.nure.hrynyshyn.core.DBSupport.DAOs;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;


/**
 * Particle implementation of data access object. Provides general CRUD operations for objects.
 */
public abstract class AbstractDAO<T> implements GenericDAO<T> {

    protected static final Logger log = Logger.getLogger(AbstractDAO.class.getName());

    protected Connection connection;

    /**
     * Returns query string for adding object to database.
     *
     * @return query.
     */
    protected abstract String getInsertQuery();

    /**
     * Returns query string for getting object from database by id.
     *
     * @return query.
     */
    protected abstract String getByIdQuery();

    /**
     * Returns query string for updating object in database.
     *
     * @return query.
     */
    protected abstract String getUpdateQuery();

    /**
     * Returns query string for deleting object from database.
     *
     * @return query.
     */
    protected abstract String getDeleteQuery();

    /**
     * Returns query string for getting all objects from database.
     *
     * @return query.
     */
    protected abstract String getGetAllQuery();

    /**
     * Provides algorithm for parsing query's result set.
     *
     * @param resultSet result set for parsing
     * @return list of objects from result set.
     * @throws SQLException if some problems during conversation with database.
     */
    protected abstract List<T> parseResultSet(ResultSet resultSet) throws SQLException;

    /**
     * Algorithm for preparing insert statement.
     *
     * @param statement statement fro preparing
     * @param object    container of data for statement attributes.
     * @throws SQLException if some problems during conversation with database
     */
    protected abstract void prepareInsertStatement(PreparedStatement statement, T object) throws SQLException;

    /**
     * Algorithm for preparing update statement.
     *
     * @param statement statement fro preparing
     * @param object    container of data for statement attributes.
     * @throws SQLException if some problems during conversation with database
     */
    protected abstract void prepareUpdateStatement(PreparedStatement statement, T object) throws SQLException;

    /**
     * Algorithm for preparing delete statement.
     *
     * @param statement statement fro preparing
     * @param object    container of data for statement attributes.
     * @throws SQLException if some problems during conversation with database
     */
    protected abstract void prepareDeleteStatement(PreparedStatement statement, T object) throws SQLException;


    @Override
    public boolean insert(T object) {
        String sql = getInsertQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareInsertStatement(statement, object);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Can't add new object to DB", e);
            return false;
        }
        log.info("New record added");
        return true;
    }

    @Override
    public T read(int id) {
        String sql = getByIdQuery();
        List<T> objects = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            objects = parseResultSet(statement.executeQuery());
            if (objects.size() != 0) {
                return objects.iterator().next();
            }
        } catch (SQLException e) {
            log.error("Can't read object from DB", e);
        }
        return null;
    }

    @Override
    public boolean update(T object) {
        String sql = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareUpdateStatement(statement, object);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Can't update DB object", e);
            return false;
        }
        log.info("Record updated");
        return true;
    }

    @Override
    public boolean delete(T object) {
        String sql = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareDeleteStatement(statement, object);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Can't delete object from", e);
            return false;
        }
        log.info("Record deleted");
        return true;
    }

    @Override
    public List<T> getAll() {
        String sql = getGetAllQuery();
        List<T> objects = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            objects = parseResultSet(statement.executeQuery());
        } catch (SQLException e) {
            log.error("Can't get all records", e);
        }
        log.info("All records obtained");
        return objects;
    }
}
