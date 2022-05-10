package ua.goit.project.model.dao;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "skills")
public class SkillsDao {
    private Integer id;
    private String industry;
    private String skillLevel;
    private Set<DevelopersDao> developers;

    public SkillsDao(Integer id, String industry, String skillLevel, Set<DevelopersDao> developers) {
        this.id = id;
        this.industry = industry;
        this.skillLevel = skillLevel;
        this.developers = developers;
    }

    public SkillsDao() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "industry")
    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    @Column(name = "skill_level")
    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    @ManyToMany(mappedBy = "skills")
    public Set<DevelopersDao> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<DevelopersDao> developers) {
        this.developers = developers;
    }
}
