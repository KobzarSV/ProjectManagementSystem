package ua.goit.jdbc.dataLayer;

import ua.goit.jdbc.config.DatabaseManager;
import ua.goit.jdbc.model.dao.ProjectsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectsRepository implements Repository<ProjectsDao> {

    private final DatabaseManager connector;

    private static final String CREATE =
            "INSERT INTO projects (name, description, company_id, customer_id) VALUES (?, ?, ?, ?)";
    private static final String READ_BY_ID = "SELECT * FROM projects p WHERE p.id = ?";
    private static final String READ_ALL = "SELECT * FROM projects p";
    private static final String UPDATE =
            "UPDATE projects p SET name = ?, description = ?, company_id = ?, customer_id = ? WHERE p.id = ?";
    private static final String DELETE = "DELETE FROM projects WHERE id = ?";

    public ProjectsRepository(DatabaseManager connector) {
        this.connector = connector;
    }

    @Override
    public void create(ProjectsDao projectsDao) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setString(1, projectsDao.getName());
            preparedStatement.setString(2, projectsDao.getDescription());
            preparedStatement.setInt(3, projectsDao.getCompanyId());
            preparedStatement.setInt(4, projectsDao.getCustomerId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<ProjectsDao> readById(int id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToProjectsDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<ProjectsDao> readAll() {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToProjectsDaoAll(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public int update(ProjectsDao projectsDao) {
        int columsUpdeted = 0;
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, projectsDao.getName());
            preparedStatement.setString(2, projectsDao.getDescription());
            preparedStatement.setInt(3, projectsDao.getCompanyId());
            preparedStatement.setInt(4, projectsDao.getCustomerId());
            preparedStatement.setInt(4, projectsDao.getId());
            columsUpdeted = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columsUpdeted;
    }

    @Override
    public void delete(ProjectsDao projectsDao) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, projectsDao.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Optional<ProjectsDao> mapToProjectsDao(ResultSet resultSet) throws SQLException {
        ProjectsDao projectsDao = null;
        while (resultSet.next()) {
            projectsDao = new ProjectsDao();
            projectsDao.setId(resultSet.getInt("id"));
            projectsDao.setName(resultSet.getString("name"));
            projectsDao.setDescription(resultSet.getString("description"));
            projectsDao.setCompanyId(resultSet.getInt("company_id"));
            projectsDao.setCustomerId(resultSet.getInt("customer_id"));
        }
        return Optional.ofNullable(projectsDao);
    }

    private List<ProjectsDao> mapToProjectsDaoAll(ResultSet resultSet) throws SQLException {
        List<ProjectsDao> projects = new ArrayList<>();
        while (resultSet.next()) {
            ProjectsDao projectsDao = null;
            projectsDao = new ProjectsDao();
            projectsDao.setId(resultSet.getInt("id"));
            projectsDao.setName(resultSet.getString("name"));
            projectsDao.setDescription(resultSet.getString("description"));
            projectsDao.setCompanyId(resultSet.getInt("company_id"));
            projectsDao.setCustomerId(resultSet.getInt("customer_id"));
            projects.add(projectsDao);
        }
        return projects;
    }
}
