package ua.goit.project.model.dao;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "projects")
public class ProjectsDao {
    private Integer id;
    private String name;
    private String description;
    private Integer companyId;
    private Integer customerId;
    private Date date;
    private Set<DevelopersDao> developers;

    public ProjectsDao(Integer id, String name, String description, Integer companyId,
                       Integer customerId, Date date, Set<DevelopersDao> developers) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.companyId = companyId;
        this.customerId = customerId;
        this.date = date;
        this.developers = developers;
    }

    public ProjectsDao() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
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

    @Column(name = "company_id")
    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    @Column(name = "customer_id")
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "developers_projects",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "developer_id")}
    )
    public Set<DevelopersDao> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<DevelopersDao> developers) {
        this.developers = developers;
    }
}
