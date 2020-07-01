package app.database;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class DatabaseTest {
    
    @Test
    public void testConnection() {
        Connection connection = null;
        connection = Database.getConnection();
        Assert.assertNotNull(connection);
        Database.closeConnection(connection);
    }

}