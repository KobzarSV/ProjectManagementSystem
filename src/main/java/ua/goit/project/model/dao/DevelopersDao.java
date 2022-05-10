package ua.goit.project.model.dao;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "developers")
public class DevelopersDao {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String gender;
    private String mail;
    private Integer companyId;
    private Integer salary;
    private Set<SkillsDao> skills;
    private Set<ProjectsDao> projects;

    public DevelopersDao(Integer id, String firstName, String lastName, Integer age,
                         String gender, String mail, Integer companyId, Integer salary,
                         Set<SkillsDao> skills, Set<ProjectsDao> projects) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.mail = mail;
        this.companyId = companyId;
        this.salary = salary;
        this.skills = skills;
        this.projects = projects;
    }

    public DevelopersDao() {
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(name = "mail")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
//    @JoinColumn(name="company_id")
    @Column(name = "company_id")
    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    @Column(name = "salary")
    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "developers_skills",
            joinColumns = {@JoinColumn(name = "developer_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")}
    )
    public Set<SkillsDao> getSkills() {
        return skills;
    }

    public void setSkills(Set<SkillsDao> skills) {
        this.skills = skills;
    }

    @ManyToMany(mappedBy = "developers")
    public Set<ProjectsDao> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectsDao> projects) {
        this.projects = projects;
    }
}
