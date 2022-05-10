package ua.goit.project.config;

import org.hibernate.Session;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseManager {

    Session getSession();
}
