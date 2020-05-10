package app.database;

import app.database.Database;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

public class DatabaseTest {
    
    @Test
    public void testConnection() {

        Assert.assertEquals("jdbc:postgresql://database-instance.c7fw4iavfo1x.us-east-2.rds.amazonaws.com/database-name", Database.getUrl());
        Assert.assertEquals("user1_admin", Database.getUser());
        Assert.assertEquals("P4$$W0RD", Database.getPassword());

        //Connection connection = Database.getConnection();
        // Assert.assertNotNull(connection);

    }

}