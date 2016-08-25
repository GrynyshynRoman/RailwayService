package ua.nure.hrynyshyn.core.DBSupport.DAOs.entitesDAO.railway;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.AbstractDAO;
import ua.nure.hrynyshyn.core.entities.railway.realEstate.Station;
import ua.nure.hrynyshyn.core.entities.railway.rollingStock.Train;

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
public class TrainDAO extends AbstractDAO<Train> {
    public List<Train> getByRouteID(int route_ID){
        String sql="SELECT * FROM trains WHERE route_ID=?";
        List<Train> trains=null;
        try(PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setInt(1,route_ID);
            trains=parseResultSet(statement.executeQuery());
        }catch (SQLException e){
            log.log(Level.INFO, "Can't execute", e);
        }
        return trains;
    }
    @Override
    protected String getInsertQuery() {
        return "INSERT INTO RAILWAY.TRAINS (route_ID) VALUES (?)";
    }

    @Override
    protected String getByIdQuery() {
        return "SELECT * FROM RAILWAY.TRAINS WHERE train_ID=?";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE RAILWAY.TRAINS SET route_ID=? WHERE train_ID=?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM RAILWAY.TRAINS WHERE train_ID=?";
    }
    @Override
    protected String getGetAllQuery() {
        return "SELECT * FROM RAILWAY.TRAINS";
    }

    @Override
    protected List<Train> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Train> trains = new ArrayList<>();
        while (resultSet.next()) {
            Train train = new Train();
            train.setTrain_ID(resultSet.getInt(1));
            train.setRoute_ID(resultSet.getInt(2));
            trains.add(train);
        }
        return trains;
    }

    @Override
    protected void prepareInsertStatement(PreparedStatement statement, Train object) throws SQLException {
        statement.setInt(1, object.getRoute_ID());
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement statement, Train object) throws SQLException {
        statement.setInt(1, object.getRoute_ID());
        statement.setInt(2, object.getTrain_ID());
    }

    @Override
    protected void prepareDeleteStatement(PreparedStatement statement, Train object) throws SQLException {
        statement.setInt(1, object.getTrain_ID());
    }

    public TrainDAO(Connection connection) {
        super.connection = connection;
    }
}
