package ua.goit.project.model.converter;

import ua.goit.project.model.dao.CustomersDao;
import ua.goit.project.model.dto.CustomersDto;

public class CustomersConverter implements Convertor<CustomersDao, CustomersDto> {

    @Override
    public CustomersDto toDto(CustomersDao dao) {
        CustomersDto dto = new CustomersDto();
        dto.setId(dao.getId());
        dto.setName(dao.getName());
        dto.setBusiness(dao.getBusiness());
        return dto;
    }

    @Override
    public CustomersDao toDao(CustomersDto dto) {
        CustomersDao dao = new CustomersDao();
        dao.setId(dto.getId());
        dao.setName(dto.getName());
        dao.setBusiness(dto.getBusiness());
        return dao;
    }
}
