package ua.goit.project.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class PostgresProvider implements DatabaseManager {

    private final HikariDataSource dataSource;

    public PostgresProvider(
            String hostname, int port, String databaseName, String username, String password, String jdbcDriver) {
        HikariConfig config = new HikariConfig();

        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Error loading postgres driver", ex);
        }

        config.setJdbcUrl(String.format("jdbc:postgresql://%s:%d/%s", hostname, port, databaseName));
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(2);
        config.setIdleTimeout(200);
        this.dataSource = new HikariDataSource(config);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
