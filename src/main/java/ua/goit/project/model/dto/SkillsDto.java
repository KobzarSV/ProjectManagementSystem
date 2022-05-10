package ua.goit.project.model.dto;

import java.util.Set;

public class SkillsDto {
    private Integer id;
    private String industry;
    private String skillLevel;
    private Set<DevelopersDto> developers;

    public SkillsDto(Integer id, String industry, String skillLevel, Set<DevelopersDto> developers) {
        this.id = id;
        this.industry = industry;
        this.skillLevel = skillLevel;
        this.developers = developers;
    }

    public SkillsDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Set<DevelopersDto> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<DevelopersDto> developers) {
        this.developers = developers;
    }
}
