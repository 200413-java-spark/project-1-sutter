package app.util;

import org.junit.Test;
import static org.junit.Assert.assertFalse;

import org.junit.Ignore;

import static app.util.AppProperties.PROPERTIES;

@Ignore
public class AppPropertiesTest {

    @Test
    public void GetPropertiesTest() {
        String user = PROPERTIES.getProperty("DATABASE_USER");
        assertFalse(user == null || user.isEmpty());
    }
    
}