package ua.goit.project.dataLayer;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import ua.goit.project.config.DatabaseManager;
import ua.goit.project.model.dao.CustomersDao;

import java.util.List;
import java.util.Optional;

public class CustomerRepository implements Repository<CustomersDao> {

    private static final String FIND_BY_ID = "FROM CustomersDao cd WHERE cd.id=:id";
    private static final String FIND_ALL = "FROM CustomersDao";

    private final DatabaseManager connector;

    public CustomerRepository(DatabaseManager connector) {
        this.connector = connector;
    }

    @Override
    public Integer create(CustomersDao customersDao) {
        Transaction transaction = null;
        try (Session session = connector.getSession()) {
            transaction = session.beginTransaction();
            session.persist(customersDao);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }

    @Override
    public Optional<CustomersDao> findById(int id) {
        try (Session session = connector.getSession()) {
            Optional<CustomersDao> customer = session.createQuery(FIND_BY_ID)
                    .setParameter("id", id)
                    .setResultListTransformer(Transformers.aliasToBean(CustomersDao.class))
                    .uniqueResultOptional();
            return customer;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<CustomersDao> findAll() {
        try (Session session = connector.getSession()) {
            List<CustomersDao> customers = session.createQuery(FIND_ALL).list();
            return customers;
        } catch (Exception throwable) {
            throwable.printStackTrace();
        }
        return List.of();
    }

    @Override
    public void update(CustomersDao customersDao) {
        Transaction transaction = null;
        try (Session session = connector.getSession()) {
            transaction = session.beginTransaction();
            session.merge(customersDao);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(CustomersDao customersDao) {
        deleteProjectForCustomersDao(customersDao);
        Transaction transaction = null;
        try (Session session = connector.getSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM CustomersDao cd WHERE cd.id=:id")
                    .setParameter("id", customersDao.getId())
                    .executeUpdate();
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void deleteProjectForCustomersDao(CustomersDao customersDao) {
        Transaction transaction = null;
        try (Session session = connector.getSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM ProjectsDao pd WHERE pd.customerId=:id")
                    .setParameter("id", customersDao.getId())
                    .executeUpdate();
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
