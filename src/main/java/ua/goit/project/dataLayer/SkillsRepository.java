package ua.goit.project.dataLayer;

import org.hibernate.Session;
import ua.goit.project.config.DatabaseManager;
import ua.goit.project.model.dao.SkillsDao;
import ua.goit.project.model.dto.SkillsDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class SkillsRepository implements Repository<SkillsDao> {

    private static final String FIND_ALL = "FROM SkillsDao";
    private static final String FIND_BY_ID = "FROM SkillsDao sd WHERE sd.id=:id";

    private final DatabaseManager connector;

    public SkillsRepository(DatabaseManager connector) {
        this.connector = connector;
    }

    @Override
    public Integer create(SkillsDao skillsDao) {
        return null;
    }

    @Override
    public Optional<SkillsDao> findById(int id) {
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
    public List<SkillsDao> findAll() {
        try (Session session = connector.getSession()) {
            return session.createQuery(FIND_ALL).list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(SkillsDao skillsDao) {

    }

    @Override
    public void delete(SkillsDao skillsDao) {

    }

    public void createDeveloperSkills(SkillsDao skillsDao) {

    }

    public List<SkillsDao> findByIds(Set<Integer> id) {
        try (Session session = connector.getSession()) {
            return session.createQuery("FROM SkillsDao sd WHERE sd.id IN :ids")
                    .setParameter("ids", id)
                    .list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
