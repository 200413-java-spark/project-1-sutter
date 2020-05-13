package app.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import app.types.State;

public class States implements Dao<State> {

    @Override
    public int count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean insert(State state) {
        // update schema.sql to auto increment primary key?
        String sql = "insert into states(id, state, lease_count) values(?, ?, ?)";
        Connection connection = null;
        try {
            connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, state.id);
            statement.setString(2, state.state);
            statement.setInt(3, state.leaseCount);
            statement.execute();
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Database.closeConnection(connection);
        }
    }
    
    @Override
    public boolean insertMany(List<State> e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean update(State e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean delete(State e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteAll() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public State select(State e) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<State> selectAll() {
        // String sql = "select * from states";
        // Statement statement = connection.createStatement();
        // ResultSet rs = statement.executeQuery(sql);
        // while (rs.next()) { ... }
        return null;
    }

    @Override
    public int getId(State e) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public State selectById(int i) {
        // TODO Auto-generated method stub
        return null;
    }

}