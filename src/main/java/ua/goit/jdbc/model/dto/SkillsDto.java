package ua.goit.jdbc.model.dto;

public class SkillsDto {
    private Integer DevelopersId;
    private String industry;
    private String skillLevel;

    public SkillsDto(Integer id, String industry, String skillLevel) {
        this.DevelopersId = id;
        this.industry = industry;
        this.skillLevel = skillLevel;
    }

    public SkillsDto() {
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
}
