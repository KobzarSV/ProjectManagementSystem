package ua.goit.jdbc.model.dto;

public class CustomersDto {
    private Integer id;
    private String name;
    private String business;

    public CustomersDto(Integer id, String name, String business) {
        this.id = id;
        this.name = name;
        this.business = business;
    }

    public CustomersDto() {
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

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    @Override
    public String toString() {
        return "\n" +
                "Customer {" +
                "id:" + id +
                ", name:'" + name + '\'' +
                ", business:'" + business + '\'' +
                '}';
    }
}
