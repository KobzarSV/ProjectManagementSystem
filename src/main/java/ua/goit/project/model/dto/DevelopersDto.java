package ua.goit.project.model.dto;

public class DevelopersDto {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private String mail;
    private Integer companyId;
    private Integer salary;
    private String industry;
    private String skillLevel;
    private String companyName;

    public DevelopersDto(Integer id, String name, Integer age, String gender, String mail, Integer companyId,
                         Integer salary, String industry, String skillLevel, String companyName) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.mail = mail;
        this.companyId = companyId;
        this.salary = salary;
        this.industry = industry;
        this.skillLevel = skillLevel;
        this.companyName = companyName;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('\n');
        builder.append("Developer {");
        if (id != null) {
            builder.append("id:").append(id).append(", ");
        }
        builder.append("name:'").append(name).append("', ");
        if (age != null) {
            builder.append("age:").append(age).append(", ");
        }
        if (gender != null) {
            builder.append("gender:'").append(gender).append("', ");
        }
        if (mail != null) {
            builder.append("mail:'").append(mail).append("', ");
        }
        if (companyId != null) {
            builder.append("company id:").append(companyId).append(", ");
        }
        if (salary != null) {
            builder.append("salary:").append(salary).append(", ");
        }
        if (industry != null) {
            builder.append("industry:").append(industry).append(", ");
        }
        if (skillLevel != null) {
            builder.append("skill level:").append(skillLevel);
        }
        builder.append(" }");
        return builder.toString();
    }
}
