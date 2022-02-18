package ua.goit.jdbc.dataLayer;

import ua.goit.jdbc.config.DatabaseManager;
import ua.goit.jdbc.model.dao.SkillsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SkillsRepository {
    private final DatabaseManager connector;

    public SkillsRepository(DatabaseManager connector) {
        this.connector = connector;
    }

    private static final String CREATE_DEVELOPER_SKILLS =
            "INSERT INTO developers_skills (developer_id, skill_id) VALUES (?, ?);";

    public void createDeveloperSkills(SkillsDao skillsDao) {
        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(CREATE_DEVELOPER_SKILLS)) {
            ps.setInt(1, skillsDao.getDeveloperSkillsId());
            ps.setInt(2, skillsDao.getSkillsDeveloperId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
