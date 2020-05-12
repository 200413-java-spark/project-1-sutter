package app.database;

import app.database.Database;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

public class DatabaseTest {
    
    @Test
    public void testConnection() {

        // Assert.assertEquals("jdbc:postgresql://ec2-3-22-171-75.us-east-2.compute.amazonaws.com:5432/db-project-1", Database.getUrl());
        // Assert.assertEquals("", Database.getUser());
        // Assert.assertEquals("", Database.getPassword());

        Connection connection = Database.getConnection();
        //Assert.assertNotNull(connection);
        Assert.assertTrue(Database.closeConnection(connection));

    }

}