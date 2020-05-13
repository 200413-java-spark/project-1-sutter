package app.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Employees implements Dao<String> {

    @Override
    public int count() {
        int count = -1;
        String sql = "select count(*) as total from employees";
        Connection connection = null;
        try {
            connection = Database.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.closeConnection(connection);
            return count;
        }
    }

    @Override
    public boolean insert(String name) {
        String sql = "insert into employees values(2, '" + name + "')";
        Connection connection = null;
        try {
            connection = Database.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(sql);
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
    public boolean update(String e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String select(String e) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> selectAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean delete(String name) {
        String sql = "delete from employees where employees.name = '" + name + "'";
        Connection connection = null;
        try {
            connection = Database.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(sql);
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
    public boolean deleteAll() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean insertMany(List<String> e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getId(String e) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String selectById(int i) {
        // TODO Auto-generated method stub
        return null;
    }

}