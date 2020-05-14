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
        int count = -1;
        String sql = "select count(*) as total from states";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = Database.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.closeConnection(connection, statement, rs);
            return count;
        }
    }

    @Override
    public boolean insert(State s) {
        // update schema.sql to auto increment primary key?
        String state = s.state;
        Integer leaseCount = s.leaseCount;
        if(state.isEmpty() || state == null || leaseCount.equals(null) || leaseCount.intValue() < 0) {
            return false;
        }
        String sql = "insert into states(id, state, lease_count) values(?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Database.getConnection();
            statement = connection.prepareStatement(sql);
            //statement.setInt(1, state.id);
            statement.setString(2, state);
            statement.setInt(3, leaseCount);
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Database.closeConnection(connection, statement);
        }
    }
    
    @Override
    public boolean insertMany(List<State> states) {
        if(states.isEmpty() || states == null) {
            return false;
        }
        String sql = "insert into states(id, state, lease_count) values(?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Database.getConnection();
            statement = connection.prepareStatement(sql);
            int id = 1;
            for(State s : states) {
                String state = s.state;
                Integer leaseCount = s.leaseCount;
                if(state.isEmpty() || state == null || leaseCount.equals(null) || leaseCount.intValue() < 0) {
                    return false;
                }
                statement.setInt(1, id++);
                statement.setString(2, state);
                statement.setInt(3, leaseCount);
                statement.addBatch();
            }
            statement.executeBatch();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Database.closeConnection(connection, statement);
        }
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
        String sql = "delete from states";
        Connection connection = null;
        Statement statement = null;
        try {
            connection = Database.getConnection();
            statement = connection.createStatement();
            statement.execute(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Database.closeConnection(connection, statement);
        }
    }

    @Override
    public State select(State s) {
        if(s.state.isEmpty() || s == null) {
            return null;
        }
        String sql = "select * from states where states.state = '" + s.state + "'";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = Database.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            if(!rs.next()) {
                return null;
            }
            int id = rs.getInt("id");
            String state = rs.getString("state");
            int leaseCount = rs.getInt("lease_count");
            return new State(id, state, leaseCount);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            Database.closeConnection(connection, statement, rs);
        }
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