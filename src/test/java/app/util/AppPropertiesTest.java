package app.util;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static app.util.AppProperties.PROPERTIES;

public class AppPropertiesTest {

    @Test
    public void GetPropertiesTest() {
        String user = PROPERTIES.getProperty("DATABASE_USER");
        assertTrue(user != null && !user.isEmpty());
    }
    
}