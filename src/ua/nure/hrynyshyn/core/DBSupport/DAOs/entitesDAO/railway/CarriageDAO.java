package ua.nure.hrynyshyn.core.DBSupport.DAOs.entitesDAO.railway;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.AbstractDAO;
import ua.nure.hrynyshyn.core.entities.railway.rollingStock.Carriage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by GrynyshynRoman on 04.08.2016.
 */
public class CarriageDAO extends AbstractDAO<Carriage> {
    public List<Carriage> getNotFullCarriages(int train_ID) {
        List<Carriage> carriages = null;
        String sql = "SELECT *\n" +
                "FROM carriages\n" +
                "WHERE train_ID = ? AND reservedSeats < totalSeats";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, train_ID);
            ResultSet rs = statement.executeQuery();
            carriages = parseResultSet(rs);
        } catch (SQLException e) {
            log.error("Can't get not full carriages", e);
        }

        return carriages;
    }

    public boolean deleteByTrainID(int id) {
        String sql = "DELETE FROM carriages WHERE train_ID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            log.info("Can't delete carriages", e);
            return false;
        }
        return true;
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO RAILWAY.CARRIAGES (train_ID, carriageNumber, type, totalSeats, reservedSeats)"
                + "VALUES(?,?,?,?,?)";
    }

    @Override
    protected String getByIdQuery() {
        return "SELECT * FROM RAILWAY.CARRIAGES WHERE carriage_ID=?";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE RAILWAY.CARRIAGES SET train_ID=?, carriageNumber=?, type=?, totalSeats=?, reservedSeats=? WHERE carriage_ID=?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM RAILWAY.CARRIAGES WHERE carriage_ID=?";
    }

    @Override
    protected String getGetAllQuery() {
        return "SELECT * FROM RAILWAY.CARRIAGES";
    }

    @Override
    protected List<Carriage> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Carriage> carriages = new ArrayList<>();
        while (resultSet.next()) {
            Carriage carriage = new Carriage();
            carriage.setCarriage_ID(resultSet.getInt(1));
            carriage.setTrain_ID(resultSet.getInt(2));
            carriage.setCarriageNumber(resultSet.getInt(3));
            carriage.setType(resultSet.getString(4));
            carriage.setTotalSeats(resultSet.getInt(5));
            carriage.setReservedSeats(resultSet.getInt(6));
            carriages.add(carriage);
        }
        return carriages;
    }

    @Override
    protected void prepareInsertStatement(PreparedStatement statement, Carriage object) throws SQLException {
        statement.setInt(1, object.getTrain_ID());
        statement.setInt(2, object.getCarriageNumber());
        statement.setString(3, object.getType());
        statement.setInt(4, object.getTotalSeats());
        statement.setInt(5, object.getReservedSeats());
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement statement, Carriage object) throws SQLException {
        statement.setInt(1, object.getTrain_ID());
        statement.setInt(2, object.getCarriageNumber());
        statement.setString(3, object.getType());
        statement.setInt(4, object.getTotalSeats());
        statement.setInt(5, object.getReservedSeats());
        statement.setInt(6, object.getCarriage_ID());
    }

    @Override
    protected void prepareDeleteStatement(PreparedStatement statement, Carriage object) throws SQLException {
        statement.setInt(1, object.getCarriage_ID());
    }

    public CarriageDAO(Connection connection) {
        super.connection = connection;
    }
}
