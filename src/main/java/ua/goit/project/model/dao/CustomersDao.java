package ua.goit.project.model.dao;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class CustomersDao {
    private Integer id;
    private String name;
    private String business;

    public CustomersDao(Integer id, String name, String business) {
        this.id = id;
        this.name = name;
        this.business = business;
    }

    public CustomersDao() {
    }

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "customer_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "business")
    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }
}
