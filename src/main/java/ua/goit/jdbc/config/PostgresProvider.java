package ua.goit.jdbc.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class PostgresProvider implements DatabaseManager {

    HikariDataSource dataSource;

    public PostgresProvider(String hostname, int port, String databaseName, String username, String password) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(String.format("jdbc:postgresql://%s:%d/%s", hostname, port, databaseName));
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(10);
        config.setIdleTimeout(100);
        this.dataSource = new HikariDataSource(config);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
