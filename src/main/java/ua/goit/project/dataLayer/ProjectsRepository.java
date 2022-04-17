package ua.goit.project.dataLayer;

import ua.goit.project.config.DatabaseManager;
import ua.goit.project.model.dao.ProjectsDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectsRepository implements Repository<ProjectsDao> {

    private final DatabaseManager connector;

    public ProjectsRepository(DatabaseManager connector) {
        this.connector = connector;
    }

    private static final String CREATE =
            "INSERT INTO projects (name, description, company_id, customer_id, date) VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM projects p WHERE p.id = ?";
    private static final String FIND_ALL =
            "SELECT p.id, p.name, p.description, companies.company_name, customers.customer_name, " +
                    "p.date, companies.company_id, customers.customer_id " +
                    "FROM projects p\n" +
                    "INNER JOIN companies ON companies.company_id = p.company_id\n" +
                    "INNER JOIN customers ON customers.customer_id = p.customer_id;";
    private static final String UPDATE =
            "UPDATE projects p SET name = ?, description = ?, company_id = ?, customer_id = ?, date = ? WHERE p.id = ?";
    private static final String DELETE = "DELETE FROM projects WHERE id = ?";
    private static final String AMOUNT_OF_SALARY_FOR_ONE_PROJECT =
            "SELECT SUM(d.salary) AS sum FROM projects p\n" +
                    "INNER JOIN developers_projects dp ON p.id = dp.project_id\n" +
                    "INNER JOIN developers d ON d.id = dp.developer_id\n" +
                    "WHERE p.id = ?;";
    private static final String PROJECTS_DATE_AND_COUNT_DEVELOPERS =
            "SELECT pr.date, pr.name, COUNT(d.id) AS count FROM projects pr\n" +
                    "INNER JOIN developers_projects dp ON dp.project_id = pr.id\n" +
                    "INNER JOIN developers d ON dp.developer_id = d.id\n" +
                    "GROUP BY pr.date, pr.name\n" +
                    "ORDER BY pr.date;";

    @Override
    public Integer create(ProjectsDao projectsDao) {
        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(CREATE)) {
            ps.setString(1, projectsDao.getName());
            ps.setString(2, projectsDao.getDescription());
            ps.setInt(3, projectsDao.getCompanyId());
            ps.setInt(4, projectsDao.getCustomerId());
            ps.setDate(5, projectsDao.getDate());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<ProjectsDao> findById(int id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            return mapToProjectsDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<ProjectsDao> findAll() {
        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = ps.executeQuery();
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
             PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setString(1, projectsDao.getName());
            ps.setString(2, projectsDao.getDescription());
            ps.setInt(3, projectsDao.getCompanyId());
            ps.setInt(4, projectsDao.getCustomerId());
            ps.setDate(5, new Date(projectsDao.getDate().getTime()));
            ps.setInt(6, projectsDao.getId());
            columsUpdeted = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columsUpdeted;
    }

    @Override
    public void delete(ProjectsDao projectsDao) {
        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE)) {
            ps.setInt(1, projectsDao.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE)) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getAmountOfSalaryForOneProject(int id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(AMOUNT_OF_SALARY_FOR_ONE_PROJECT)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            return mapToSum(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<ProjectsDao> getProjectsDateAndCountDev() {
        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(PROJECTS_DATE_AND_COUNT_DEVELOPERS)) {
            ResultSet resultSet = ps.executeQuery();
            return mapToProjectsDateDevDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
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
            projectsDao.setDate(resultSet.getDate("date"));
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
            projectsDao.setCompanyName(resultSet.getString("company_name"));
            projectsDao.setCustomerId(resultSet.getInt("customer_id"));
            projectsDao.setCustomerName(resultSet.getString("customer_name"));
            projectsDao.setDate(resultSet.getDate("date"));

            projects.add(projectsDao);
        }
        return projects;
    }

    private int mapToSum(ResultSet resultSet) throws SQLException {
        int result = 0;
        while (resultSet.next()) {
            result = resultSet.getInt("sum");
        }
        return result;
    }

    private List<ProjectsDao> mapToProjectsDateDevDao(ResultSet resultSet) throws SQLException {
        List<ProjectsDao> projects = new ArrayList<>();
        while (resultSet.next()) {
            ProjectsDao projectsDao = null;
            projectsDao = new ProjectsDao();
            projectsDao.setDate(resultSet.getDate("date"));
            projectsDao.setName(resultSet.getString("name"));
            projectsDao.setCountDevelopers(resultSet.getInt("count"));
            projects.add(projectsDao);
        }
        return projects;
    }
}
