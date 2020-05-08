package app.database;

import static app.util.AppProperties.PROPERTIES;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    static final Database DATABASE = new Database();
    private static final String URL = PROPERTIES.getProperty("DATABASE_URL");
    private static final String USER = PROPERTIES.getProperty("DATABASE_USER");
    private static final String PASSWORD = PROPERTIES.getProperty("DATABASE_PASSWORD");

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public String getUrl() {
        return URL;
    }

    public String getUser() {
        return USER;
    }

    public String getPassword() {
        return PASSWORD;
    }

}