package app.database;

import static app.util.AppProperties.PROPERTIES;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String URL = PROPERTIES.getProperty("DATABASE_URL");
    private static final String USER = PROPERTIES.getProperty("DATABASE_USER");
    private static final String PASSWORD = PROPERTIES.getProperty("DATABASE_PASSWORD");

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(final Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getUrl() {
        return URL;
    }

    public static String getUser() {
        return USER;
    }

    public static String getPassword() {
        return PASSWORD;
    }

}