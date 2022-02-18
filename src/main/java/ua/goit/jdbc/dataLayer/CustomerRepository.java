package ua.goit.jdbc.dataLayer;

import ua.goit.jdbc.config.DatabaseManager;
import ua.goit.jdbc.model.dao.CustomersDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerRepository implements Repository<CustomersDao> {
    private final DatabaseManager connector;

    public CustomerRepository(DatabaseManager conector) {
        this.connector = conector;
    }

    private static final String CREATE =
            "INSERT INTO customers (name, business) VALUES (?, ?);";
    private static final String READ_BY_ID = "SELECT * FROM customers c WHERE c.id = ?;";
    private static final String READ_ALL = "SELECT * FROM customers;";
    private static final String UPDATE =
            "UPDATE customers c SET name = ?, business = ? WHERE c.id = ?;";
    private static final String DELETE = "DELETE FROM customers WHERE id = ?;";

    @Override
    public void create(CustomersDao customersDao) {
        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(CREATE)) {
            ps.setString(1, customersDao.getName());
            ps.setString(2, customersDao.getBusiness());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<CustomersDao> readById(int id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(READ_BY_ID)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            return mapToCustomerDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<CustomersDao> readAll() {
        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(READ_ALL)) {
            ResultSet resultSet = ps.executeQuery();
            return mapToCustomerListDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public int update(CustomersDao customersDao) {
        int columnsUpdated = 0;
        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setString(1, customersDao.getName());
            ps.setString(2, customersDao.getBusiness());
            ps.setInt(3, customersDao.getId());
            columnsUpdated = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnsUpdated;
    }

    @Override
    public void delete(CustomersDao customersDao) {
        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE)) {
            ps.setInt(1, customersDao.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Optional<CustomersDao> mapToCustomerDao(ResultSet resultSet) throws SQLException {
        CustomersDao customersDao = null;
        while (resultSet.next()) {
            customersDao = new CustomersDao();
            customersDao.setId(resultSet.getInt("id"));
            customersDao.setName(resultSet.getString("name"));
            customersDao.setBusiness(resultSet.getString("business"));
        }
        return Optional.ofNullable(customersDao);
    }

    private List<CustomersDao> mapToCustomerListDao(ResultSet resultSet) throws SQLException {
        List<CustomersDao> cuctomers = new ArrayList<>();
        while (resultSet.next()) {
            CustomersDao customersDao = null;
            customersDao = new CustomersDao();
            customersDao.setId(resultSet.getInt("id"));
            customersDao.setName(resultSet.getString("name"));
            customersDao.setBusiness(resultSet.getString("business"));
            cuctomers.add(customersDao);
        }
        return cuctomers;
    }
}
