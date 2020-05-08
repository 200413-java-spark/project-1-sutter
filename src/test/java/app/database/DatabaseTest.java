package app.database;

import static app.database.Database.DATABASE;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

public class DatabaseTest {
    
    @Test
    public void testConnection() {

        Assert.assertNotNull(DATABASE);

        Assert.assertEquals("jdbc:postgresql://database-instance.c7fw4iavfo1x.us-east-2.rds.amazonaws.com/database-name", DATABASE.getUrl());
        Assert.assertEquals("user1_admin", DATABASE.getUser());
        Assert.assertEquals("P4$$W0RD", DATABASE.getPassword());

        //Connection connection = DATABASE.getConnection();
        // Assert.assertNotNull(connection);

    }

}