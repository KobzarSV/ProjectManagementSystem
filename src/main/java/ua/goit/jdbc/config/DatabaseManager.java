package ua.goit.jdbc.config;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseManager {
    Connection getConnection() throws SQLException;
}
