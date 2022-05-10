package ua.goit.project.dataLayer;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goit.project.config.DatabaseManager;
import ua.goit.project.model.dao.DevelopersDao;

import java.util.*;

public class DevelopersRepository implements Repository<DevelopersDao> {

    private static final String FIND_BY_ID = "FROM DevelopersDao dd WHERE dd.id=:id";
    private static final String FIND_ALL = "FROM DevelopersDao";

    private final DatabaseManager connector;

    public DevelopersRepository(DatabaseManager connector) {
        this.connector = connector;
    }

    @Override
    public Integer create(DevelopersDao developersDao) {
        Transaction transaction = null;
        try (Session session = connector.getSession()) {
            transaction = session.beginTransaction();
            session.persist(developersDao);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<DevelopersDao> findById(int id) {
        try (Session session = connector.getSession()) {
            return session.createQuery(FIND_BY_ID)
                    .setParameter("id", id)
                    .uniqueResultOptional();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<DevelopersDao> findAll() {
        try (Session session = connector.getSession()) {
            return session.createQuery(FIND_ALL).list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return List.of();
    }

    @Override
    public void update(DevelopersDao developersDao) {
        Transaction transaction = null;
        try (Session session = connector.getSession()) {
            transaction = session.beginTransaction();
            session.merge(developersDao);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(DevelopersDao developersDao) {
        deleteDevelopersProjectsDao(developersDao);
        Transaction transaction = null;
        try (Session session = connector.getSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM DevelopersDao dd WHERE dd.id=:id")
                    .setParameter("id", developersDao.getId())
                    .executeUpdate();
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void deleteDevelopersProjectsDao(DevelopersDao developersDao) {
        Transaction transaction = null;
        try (Session session = connector.getSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM DevelopersProjectsDao dpd WHERE dpd.developerId=:id")
                    .setParameter("id", developersDao.getId())
                    .executeUpdate();
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<DevelopersDao> findByIds(Set<Integer> id) {
        try (Session session = connector.getSession()) {
            return session.createQuery("FROM DevelopersDao dd WHERE dd.id IN :ids")
                    .setParameter("ids", id)
                    .list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
