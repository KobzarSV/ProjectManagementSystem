package ua.goit.jdbc.model.dto;

public class ProjectsDto {
    private Integer id;
    private String name;
    private String description;
    private Integer companyId;
    private Integer customerId;

    public ProjectsDto(Integer id, String name, String description, Integer companyId, Integer customerId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.companyId = companyId;
        this.customerId = customerId;
    }

    public ProjectsDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "ProjectsDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", companyId=" + companyId +
                ", customerId=" + customerId +
                '}'+ '\n';
    }
}
