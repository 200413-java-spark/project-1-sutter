package app.database;

import app.database.Database;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

public class DatabaseTest {
    
    @Test
    public void testConnection() {
        Connection connection = Database.getConnection();
        Assert.assertTrue(Database.closeConnection(connection));
    }

}