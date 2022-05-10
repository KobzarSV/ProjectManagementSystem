package ua.goit.project.dataLayer;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import ua.goit.project.config.DatabaseManager;
import ua.goit.project.model.dao.CompaniesDao;

import java.util.List;
import java.util.Optional;

public class CompanyRepository implements Repository<CompaniesDao> {

    private static final String FIND_BY_ID = "FROM CompaniesDao compd WHERE compd.id=:id";
    private static final String FIND_ALL = "FROM CompaniesDao";

    private final DatabaseManager connector;

    public CompanyRepository(DatabaseManager connector) {
        this.connector = connector;
    }

    @Override
    public Integer create(CompaniesDao companiesDao) {
        Transaction transaction = null;
        try (Session session = connector.getSession()) {
            transaction = session.beginTransaction();
            session.persist(companiesDao);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<CompaniesDao> findById(int id) {
        try (Session session = connector.getSession()) {
            Optional<CompaniesDao> company = session.createQuery(FIND_BY_ID)
                    .setParameter("id", id)
                    .setResultListTransformer(Transformers.aliasToBean(CompaniesDao.class))
                    .uniqueResultOptional();
            return company;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<CompaniesDao> findAll() {
        try (Session session = connector.getSession()) {
            List<CompaniesDao> companies = session.createQuery(FIND_ALL).list();
            return companies;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return List.of();
    }

    @Override
    public void update(CompaniesDao companyDao) {
        Transaction transaction = null;
        try (Session session = connector.getSession()) {
            transaction = session.beginTransaction();
            session.merge(companyDao);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(CompaniesDao companyDao) {
        deleteDeveloperForCompanyDao(companyDao);
        deleteProjectForCompanyDao(companyDao);
        Transaction transaction = null;
        try (Session session = connector.getSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM CompaniesDao cd WHERE cd.id=:id")
                    .setParameter("id", companyDao.getId())
                    .executeUpdate();
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void deleteDeveloperForCompanyDao(CompaniesDao companyDao) {
        Transaction transaction = null;
        try (Session session = connector.getSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM DevelopersDao dd WHERE dd.companyId=:id")
                    .setParameter("id", companyDao.getId())
                    .executeUpdate();
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteProjectForCompanyDao(CompaniesDao companyDao) {
        Transaction transaction = null;
        try (Session session = connector.getSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM ProjectsDao pd WHERE pd.companyId=:id")
                    .setParameter("id", companyDao.getId())
                    .executeUpdate();
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
