package ua.goit.project.model.dao;

import jakarta.persistence.*;

@Entity
@Table(name = "companies")
public class CompaniesDao {
    private Integer id;
    private String name;
    private String description;
    private Integer employees;

    public CompaniesDao(Integer id, String name, String description, Integer employees) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.employees = employees;
    }

    public CompaniesDao() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "company_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "employees")
    public Integer getEmployees() {
        return employees;
    }

    public void setEmployees(Integer employees) {
        this.employees = employees;
    }
}
