package ua.goit.project.dataLayer;

import ua.goit.project.config.DatabaseManager;
import ua.goit.project.model.dao.DevelopersDao;

import java.sql.*;
import java.util.*;

public class DevelopersRepository implements Repository<DevelopersDao> {
    private final DatabaseManager connector;

    public DevelopersRepository(DatabaseManager connector) {
        this.connector = connector;
    }

    private static final String CREATE =
            "INSERT INTO developers (first_name, last_name, age, gender, mail, company_id, salary) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String FIND_BY_ID =
            "SELECT d.id, d.first_name, d.last_name, d.age, d.gender, d.mail, d.company_id, d.salary, " +
                    "s.industry, s.skill_level FROM developers d \n" +
                    "LEFT JOIN developers_skills ds ON d.id = ds.developer_id\n" +
                    "LEFT JOIN skills s ON s.id = ds.skill_id\n" +
                    "WHERE d.id = ?;";
    private static final String FIND_ALL =
            "SELECT d.id, d.first_name, d.last_name, d.age, d.gender, d.mail, c.company_id, c.company_name, d.salary " +
                    "FROM developers d\n" +
                    "INNER JOIN companies c ON d.company_id = c.company_id\n" +
                    "ORDER BY d.id;";
    private static final String UPDATE =
            "UPDATE developers d SET first_name = ?, last_name = ?, age = ?, gender = ?, mail = ?, " +
                    "company_id = ?, salary = ? WHERE d.id = ?;";
    private static final String DELETE = "DELETE FROM developers_skills WHERE developer_id = ?;\n" +
            "DELETE FROM developers WHERE id = ?;";
    private static final String DEVELOPERS_OF_PROJECT_BY_ID =
            "SELECT * FROM developers d\n" +
                    "INNER JOIN developers_projects dp ON dp.developer_id = d.id\n" +
                    "INNER JOIN projects p ON dp.project_id = p.id\n" +
                    "WHERE p.id = ?;";
    private static final String DEVELOPERS_OF_PROJECT_BY_NAME =
            "SELECT * FROM developers d\n" +
                    "INNER JOIN developers_projects dp ON dp.developer_id = d.id\n" +
                    "INNER JOIN projects p ON dp.project_id = p.id\n" +
                    "WHERE p.name = ?;";
    private static final String ALL_DEVELOPERS_BY_INDUSTRY =
            "SELECT * FROM developers d\n" +
                    "INNER JOIN developers_skills ds ON d.id = ds.developer_id\n" +
                    "INNER JOIN skills s ON s.id = ds.skill_id\n" +
                    "WHERE s.industry = ?;";
    private static final String ALL_DEVELOPERS_BY_SKILL_LEVEL =
            "SELECT * FROM developers d\n" +
                    "INNER JOIN developers_skills ds ON d.id = ds.developer_id\n" +
                    "INNER JOIN skills s ON s.id = ds.skill_id\n" +
                    "WHERE s.skill_level = ?;";
    private static final String ALL_INDUSTRY_BY_DEVELOPER_ID =
            "SELECT s.industry, s.skill_level FROM developers d\n" +
                    "INNER JOIN developers_skills ds ON d.id = ds.developer_id\n" +
                    "INNER JOIN skills s ON s.id = ds.skill_id\n" +
                    "WHERE d.id = ?;";
    private static final String ALL_INDUSTRY_BY_ALL_DEVELOPERS =
            "SELECT d.id, s.industry, s.skill_level FROM developers d\n" +
                    "INNER JOIN developers_skills ds ON d.id = ds.developer_id\n" +
                    "INNER JOIN skills s ON s.id = ds.skill_id";

    @Override
    public Integer create(DevelopersDao developersDao) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, developersDao.getFirstName());
            preparedStatement.setString(2, developersDao.getLastName());
            preparedStatement.setInt(3, developersDao.getAge());
            preparedStatement.setString(4, developersDao.getGender());
            preparedStatement.setString(5, developersDao.getMail());
            preparedStatement.setInt(6, developersDao.getCompanyId());
            preparedStatement.setInt(7, developersDao.getSalary());
            preparedStatement.execute();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<DevelopersDao> findById(int id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToDevelopersDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<DevelopersDao> findAll() {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToDevelopersList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public int update(DevelopersDao developersDao) {
        int columnsUpdated = 0;
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, developersDao.getFirstName());
            preparedStatement.setString(2, developersDao.getLastName());
            preparedStatement.setInt(3, developersDao.getAge());
            preparedStatement.setString(4, developersDao.getGender());
            preparedStatement.setString(5, developersDao.getMail());
            preparedStatement.setInt(6, developersDao.getCompanyId());
            preparedStatement.setInt(7, developersDao.getSalary());
            preparedStatement.setInt(8, developersDao.getId());
            columnsUpdated = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnsUpdated;
    }

    @Override
    public void delete(DevelopersDao developersDao) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, developersDao.getId());
            preparedStatement.setInt(2, developersDao.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE)) {
            ps.setInt(1, id);
            ps.setInt(2, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DevelopersDao> getDevOfProjectById(int id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DEVELOPERS_OF_PROJECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToDevelopersList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public List<DevelopersDao> getDevOfProjectByName(String nameProject) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DEVELOPERS_OF_PROJECT_BY_NAME)) {
            preparedStatement.setString(1, nameProject);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToDevelopersList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public List<DevelopersDao> getAllDevelopersByIndustry(String industry) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ALL_DEVELOPERS_BY_INDUSTRY)) {
            preparedStatement.setString(1, industry);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToDevelopersList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public List<DevelopersDao> getAllDevelopersBySkillLevel(String skillLevel) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ALL_DEVELOPERS_BY_SKILL_LEVEL)) {
            preparedStatement.setString(1, skillLevel);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToDevelopersList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public Map<String, String> findIndustryByDeveloperId(int id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(ALL_INDUSTRY_BY_DEVELOPER_ID)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            return mapToIndustryByDeveloper(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Map<String, String>> findIndustryByAllDeveloper() {
        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = ps.executeQuery();
            return mapToIndustry(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return List.of();
    }

    private Optional<DevelopersDao> mapToDevelopersDao(ResultSet resultSet) throws SQLException {
        DevelopersDao developersDao = null;
        while (resultSet.next()) {
            developersDao = new DevelopersDao();
            developersDao.setId(resultSet.getInt("id"));
            developersDao.setFirstName(resultSet.getString("first_name"));
            developersDao.setLastName(resultSet.getString("last_name"));
            developersDao.setAge(resultSet.getInt("age"));
            developersDao.setGender(resultSet.getString("gender"));
            developersDao.setMail(resultSet.getString("mail"));
            developersDao.setCompanyId(resultSet.getInt("company_id"));
            developersDao.setSalary(resultSet.getInt("salary"));
            developersDao.setIndustry(resultSet.getString("industry"));
            developersDao.setSkillLevel(resultSet.getString("skill_level"));
        }
        return Optional.ofNullable(developersDao);
    }

    private List<DevelopersDao> mapToDevelopersList(ResultSet resultSet) throws SQLException {
        List<DevelopersDao> developers = new ArrayList<>();
        while (resultSet.next()) {
            DevelopersDao developersDao = null;
            developersDao = new DevelopersDao();
            developersDao.setId(resultSet.getInt("id"));
            developersDao.setFirstName(resultSet.getString("first_name"));
            developersDao.setLastName(resultSet.getString("last_name"));
            developersDao.setAge(resultSet.getInt("age"));
            developersDao.setGender(resultSet.getString("gender"));
            developersDao.setMail(resultSet.getString("mail"));
            developersDao.setCompanyId(resultSet.getInt("company_id"));
            developersDao.setCompanyName(resultSet.getString("company_name"));
            developersDao.setSalary(resultSet.getInt("salary"));
            developers.add(developersDao);
        }
        return developers;
    }

    private Map<String, String> mapToIndustryByDeveloper(ResultSet rs) throws SQLException {
        Map<String, String> industry = new HashMap();
        while (rs.next()) {
            industry.put(rs.getString("industry"), rs.getString("skill_level"));
        }
        return industry;
    }

    private List<Map<String, String>> mapToIndustry(ResultSet rs) throws SQLException {
        List<Map<String, String>> allIndustry = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            Map<String, String> industry = findIndustryByDeveloperId(id);
            allIndustry.add(industry);
        }
        return allIndustry;
    }
}
