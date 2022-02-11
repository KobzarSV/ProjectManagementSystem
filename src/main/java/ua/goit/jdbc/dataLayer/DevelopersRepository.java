package ua.goit.jdbc.dataLayer;

import ua.goit.jdbc.config.DatabaseManager;
import ua.goit.jdbc.model.dao.DevelopersDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DevelopersRepository implements Repository<DevelopersDao> {
    private final DatabaseManager connector;

    private static final String CREATE =
            "INSERT INTO developers (first_name, last_name, age, gender, mail, company_id, salary) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String READ_BY_ID = "SELECT * FROM developers d WHERE d.id = ?";
    private static final String READ_ALL = "SELECT * FROM developers d";
    private static final String UPDATE =
            "UPDATE developers d SET first_name = ?, last_name = ?, age = ?, gender = ?, mail = ?, company_id = ?, salary = ? WHERE d.id = ?";
    private static final String DELETE = "DELETE FROM developers WHERE id = ?";

    public DevelopersRepository(DatabaseManager connector) {
        this.connector = connector;
    }

    @Override
    public void create(DevelopersDao developersDao) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setString(1, developersDao.getFirstName());
            preparedStatement.setString(2, developersDao.getLastName());
            preparedStatement.setInt(3, developersDao.getAge());
            preparedStatement.setString(4, developersDao.getGender());
            preparedStatement.setString(5, developersDao.getMail());
            preparedStatement.setInt(6, developersDao.getCompanyId());
            preparedStatement.setInt(7, developersDao.getSalary());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<DevelopersDao> readById(int id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToDevelopersDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<DevelopersDao> readAll() {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToDevelopersDaoAll(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public int update(DevelopersDao developersDao) {
        int columsUpdeted = 0;
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
            columsUpdeted = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columsUpdeted;
    }

    @Override
    public void delete(DevelopersDao developersDao) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, developersDao.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        }
        return Optional.ofNullable(developersDao);
    }

    private List<DevelopersDao> mapToDevelopersDaoAll(ResultSet resultSet) throws SQLException {
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
            developersDao.setSalary(resultSet.getInt("salary"));
            developers.add(developersDao);
        }
        return developers;
    }
}
