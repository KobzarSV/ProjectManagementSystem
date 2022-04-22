package ua.goit.project.dataLayer;

import ua.goit.project.config.DatabaseManager;
import ua.goit.project.model.dao.CustomersDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerRepository implements Repository<CustomersDao> {
    private final DatabaseManager connector;

    public CustomerRepository(DatabaseManager connector) {
        this.connector = connector;
    }

    private static final String CREATE =
            "INSERT INTO customers (customer_name, business) VALUES (?, ?);";
    private static final String FIND_BY_ID = "SELECT * FROM customers c WHERE c.customer_id = ?;";
    private static final String FIND_ALL = "SELECT * FROM customers;";
    private static final String UPDATE =
            "UPDATE customers c SET customer_name = ?, business = ? WHERE c.customer_id = ?;";
    private static final String DELETE =
            "UPDATE projects SET customer_id = null WHERE customer_id = ?;\n" +
                    "DELETE FROM customers WHERE customer_id = ?;";

    @Override
    public Integer create(CustomersDao customersDao) {
        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(CREATE)) {
            ps.setString(1, customersDao.getName());
            ps.setString(2, customersDao.getBusiness());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<CustomersDao> findById(int id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            return mapToCustomerDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<CustomersDao> findAll() {
        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_ALL)) {
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

    private Optional<CustomersDao> mapToCustomerDao(ResultSet resultSet) throws SQLException {
        CustomersDao customersDao = null;
        while (resultSet.next()) {
            customersDao = new CustomersDao();
            customersDao.setId(resultSet.getInt("customer_id"));
            customersDao.setName(resultSet.getString("customer_name"));
            customersDao.setBusiness(resultSet.getString("business"));
        }
        return Optional.ofNullable(customersDao);
    }

    private List<CustomersDao> mapToCustomerListDao(ResultSet resultSet) throws SQLException {
        List<CustomersDao> cuctomers = new ArrayList<>();
        while (resultSet.next()) {
            CustomersDao customersDao = null;
            customersDao = new CustomersDao();
            customersDao.setId(resultSet.getInt("customer_id"));
            customersDao.setName(resultSet.getString("customer_name"));
            customersDao.setBusiness(resultSet.getString("business"));
            cuctomers.add(customersDao);
        }
        return cuctomers;
    }
}
