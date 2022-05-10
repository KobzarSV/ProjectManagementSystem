package ua.goit.project.dataLayer;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goit.project.config.DatabaseManager;
import ua.goit.project.model.dao.ProjectsDao;

import java.util.List;
import java.util.Optional;

public class ProjectsRepository implements Repository<ProjectsDao> {

    private static final String FIND_BY_ID = "FROM ProjectsDao pd WHERE pd.id=:id";
    private static final String FIND_ALL = "FROM ProjectsDao";

    private final DatabaseManager connector;

    public ProjectsRepository(DatabaseManager connector) {
        this.connector = connector;
    }

    @Override
    public Integer create(ProjectsDao projectsDao) {
        Transaction transaction = null;
        try (Session session = connector.getSession()) {
            transaction = session.beginTransaction();
            session.persist(projectsDao);
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
    public Optional<ProjectsDao> findById(int id) {
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
    public List<ProjectsDao> findAll() {
        try (Session session = connector.getSession()) {
            return session.createQuery(FIND_ALL).list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return List.of();
    }

    @Override
    public void update(ProjectsDao projectsDao) {
        Transaction transaction = null;
        try (Session session = connector.getSession()) {
            transaction = session.beginTransaction();
            session.merge(projectsDao);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(ProjectsDao projectsDao) {
        Transaction transaction = null;
        try (Session session = connector.getSession()) {
            transaction = session.beginTransaction();
            session.remove(projectsDao);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
