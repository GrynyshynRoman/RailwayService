package ua.nure.hrynyshyn.core.DBSupport.DAOs;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by GrynyshynRoman on 18.07.2016.
 */
public interface GenericDAO<T> {
    /**
     * Inserts new object to DB.
     * @param object
     * @throws SQLException
     */
    public boolean insert(T object) throws SQLException;

    public T read(int id) throws SQLException;

    public boolean update(T object) throws SQLException;

    public boolean delete(T object) throws SQLException;

    public List<T> getAll() throws SQLException;


}
