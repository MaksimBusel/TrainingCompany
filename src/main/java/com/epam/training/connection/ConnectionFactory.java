package main.java.com.epam.training.connection;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    public static ProxyConnection create(ConnectionPool pool) {
        Connection connection = null;
        try {
            connection = getConnection();
        } catch (SQLException | IOException e) {
            e.printStackTrace(); //logg
        }
        return new ProxyConnection(connection, pool);
    }

    private static Connection getConnection() throws SQLException, IOException {
        Properties properties = new Properties();
        try (InputStream input = Files.newInputStream(Paths.get("src/main/resources/database.properties"))) {
            properties.load(input);
        }
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        return DriverManager.getConnection(url, username, password);
    }
}
