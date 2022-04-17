package ua.goit.project.model.dao;

public class SkillsDao {
    private Integer DevelopersId;
    private String industry;
    private String skillLevel;
    private Integer developerSkillsId;
    private Integer skillsDeveloperId;

    public SkillsDao(Integer id, String industry, String skillLevel, Integer developerSkillsId, Integer skillsDeveloperId) {
        this.DevelopersId = id;
        this.industry = industry;
        this.skillLevel = skillLevel;
        this.developerSkillsId = developerSkillsId;
        this.skillsDeveloperId = skillsDeveloperId;
    }

    public SkillsDao() {
    }

    public Integer getDevelopersId() {
        return DevelopersId;
    }

    public void setDevelopersId(Integer developersId) {
        this.DevelopersId = developersId;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    public Integer getDeveloperSkillsId() {
        return developerSkillsId;
    }

    public void setDeveloperSkillsId(Integer developerSkillsId) {
        this.developerSkillsId = developerSkillsId;
    }

    public Integer getSkillsDeveloperId() {
        return skillsDeveloperId;
    }

    public void setSkillsDeveloperId(Integer skillsDeveloperId) {
        this.skillsDeveloperId = skillsDeveloperId;
    }
}
