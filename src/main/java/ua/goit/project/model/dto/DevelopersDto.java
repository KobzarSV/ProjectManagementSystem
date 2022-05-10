package ua.goit.project.model.dto;

import java.util.Set;

public class DevelopersDto {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private String mail;
    private Integer companyId;
    private Integer salary;
    private Set<SkillsDto> skills;

    public DevelopersDto(Integer id, String name, Integer age, String gender, String mail,
                         Integer companyId, Integer salary, Set<SkillsDto> skills) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.mail = mail;
        this.companyId = companyId;
        this.salary = salary;
        this.skills = skills;
    }

    public DevelopersDto() {
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

    public Set<SkillsDto> getSkills() {
        return skills;
    }

    public void setSkills(Set<SkillsDto> skills) {
        this.skills = skills;
    }
}
