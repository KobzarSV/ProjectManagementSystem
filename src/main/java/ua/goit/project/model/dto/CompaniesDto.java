package ua.goit.project.model.dto;

public class CompaniesDto {
    private Integer id;
    private String name;
    private String description;
    private Integer employees;

    public CompaniesDto(Integer id, String name, String description, Integer employees) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.employees = employees;
    }

    public CompaniesDto() {
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

    public Integer getEmployees() {
        return employees;
    }

    public void setEmployees(Integer employees) {
        this.employees = employees;
    }
}
