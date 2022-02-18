package ua.goit.jdbc.model.dao;

public class DevelopersDao {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String gender;
    private String mail;
    private Integer companyId;
    private Integer salary;
    private String industry;
    private String skillLevel;

    public DevelopersDao(Integer id, String firstName, String lastName, Integer age, String gender,
                         String mail, Integer companyId, Integer salary, String industry, String skillLevel) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.mail = mail;
        this.companyId = companyId;
        this.salary = salary;
        this.industry = industry;
        this.skillLevel = skillLevel;
    }

    public DevelopersDao() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
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
