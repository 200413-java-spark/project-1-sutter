package app.util;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static app.util.AppProperties.PROPERTIES;

public class AppPropertiesTest {

    @Test
    public void GetPropertiesTest() {
        String user = PROPERTIES.getProperty("DATABASE_USER");
        assertEquals("user1", user);
    }
    
}