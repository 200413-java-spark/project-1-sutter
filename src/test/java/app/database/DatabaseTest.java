package app.database;

import app.database.Database;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

public class DatabaseTest {
    
    @Test
    public void testConnection() {
        Connection connection = null;
        connection = Database.getConnection();
        Assert.assertNotNull(connection);
        Database.closeConnection(connection);
    }

}