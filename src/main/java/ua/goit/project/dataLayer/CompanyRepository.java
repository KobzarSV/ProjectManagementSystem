package ua.goit.project.dataLayer;

import ua.goit.project.config.DatabaseManager;
import ua.goit.project.model.dao.CompanyDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompanyRepository implements Repository<CompanyDao> {

    private final DatabaseManager connector;

    public CompanyRepository(DatabaseManager connector) {
        this.connector = connector;
    }

    private static final String CREATE =
            "INSERT INTO companies (company_name, description, number_of_employees) VALUES (?, ?, ?);";
    private static final String FIND_BY_ID = "SELECT * FROM companies c WHERE c.company_id = ?;";
    private static final String FIND_ALL = "SELECT * FROM companies;";
    private static final String UPDATE =
            "UPDATE companies c SET company_name = ?, description = ?, number_of_employees = ? WHERE c.company_id = ?;";
    private static final String DELETE =
            "DELETE FROM projects WHERE company_id = ?;\n" +
                    "UPDATE developers SET company_id = null WHERE company_id = ?;\n" +
                    "DELETE FROM companies WHERE company_id = ?;";

    @Override
    public Integer create(CompanyDao companyDao) {
        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(CREATE)) {
            ps.setString(1, companyDao.getName());
            ps.setString(2, companyDao.getDescription());
            ps.setInt(3, companyDao.getNumberOfEmployees());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<CompanyDao> findById(int id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            return mapToCompaniesDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<CompanyDao> findAll() {
        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = ps.executeQuery();
            return mapToCompaniesListDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public int update(CompanyDao companyDao) {
        int columnsUpdated = 0;
        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setString(1, companyDao.getName());
            ps.setString(2, companyDao.getDescription());
            ps.setInt(3, companyDao.getNumberOfEmployees());
            ps.setInt(4, companyDao.getId());
            columnsUpdated = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnsUpdated;
    }

    @Override
    public void delete(CompanyDao companyDao) {
        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE)) {
            ps.setInt(1, companyDao.getId());
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
            ps.setInt(2, id);
            ps.setInt(3, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Optional<CompanyDao> mapToCompaniesDao(ResultSet resultSet) throws SQLException {
        CompanyDao companyDao = null;
        while (resultSet.next()) {
            companyDao = new CompanyDao();
            companyDao.setId(resultSet.getInt("company_id"));
            companyDao.setName(resultSet.getString("company_name"));
            companyDao.setDescription(resultSet.getString("description"));
            companyDao.setNumberOfEmployees(resultSet.getInt("number_of_employees"));
        }
        return Optional.ofNullable(companyDao);
    }

    private List<CompanyDao> mapToCompaniesListDao(ResultSet resultSet) throws SQLException {
        List<CompanyDao> companies = new ArrayList<>();
        while (resultSet.next()) {
            CompanyDao companyDao = null;
            companyDao = new CompanyDao();
            companyDao.setId(resultSet.getInt("company_id"));
            companyDao.setName(resultSet.getString("company_name"));
            companyDao.setDescription(resultSet.getString("description"));
            companyDao.setNumberOfEmployees(resultSet.getInt("number_of_employees"));
            companies.add(companyDao);
        }
        return companies;
    }
}
