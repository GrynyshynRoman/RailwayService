package ua.nure.hrynyshyn.core.DBSupport.DAOs;

import java.sql.SQLException;
import java.util.List;

/**
 * Data access object generic interface.
 */
public interface GenericDAO<T> {
    /**
     * Inserts new object into DB.
     *
     * @param object object for adding.
     * @throws SQLException if some problems during conversation with database.
     */
    boolean insert(T object) throws SQLException;

    /**
     * Returns object from database by it's id.
     *
     * @param id object's id.
     * @return object instance.
     * @throws SQLException if some problems during conversation with database.
     */
    T read(int id) throws SQLException;

    /**
     * Updates object in database.
     *
     * @param object object for updating.
     * @return true if operation success.
     * @throws SQLException if some problems during conversation with database.
     */
    boolean update(T object) throws SQLException;

    /**
     * Deletes object from database.
     *
     * @param object for deleting.
     * @return true if operation success.
     * @throws SQLException if some problems during conversation with database.
     */
    boolean delete(T object) throws SQLException;

    /**
     * Returns all rows from database table.
     *
     * @return list of objects.
     * @throws SQLException if some problems during conversation with database.
     */
    List<T> getAll() throws SQLException;


}
