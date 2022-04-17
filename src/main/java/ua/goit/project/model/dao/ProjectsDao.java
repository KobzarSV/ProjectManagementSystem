package ua.goit.project.model.dao;

import java.sql.Date;

public class ProjectsDao {
    private Integer id;
    private String name;
    private String description;
    private Integer companyId;
    private Integer customerId;
    private Date date;
    private Integer countDevelopers;
    private String companyName;
    private String customerName;

    public ProjectsDao(Integer id, String name, String description, Integer companyId, Integer customerId,
                       Date date, Integer countDevelopers, String companyName, String customerName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.companyId = companyId;
        this.customerId = customerId;
        this.date = date;
        this.countDevelopers = countDevelopers;
        this.companyName = companyName;
        this.customerName = customerName;
    }

    public ProjectsDao() {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getCountDevelopers() {
        return countDevelopers;
    }

    public void setCountDevelopers(Integer countDevelopers) {
        this.countDevelopers = countDevelopers;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
