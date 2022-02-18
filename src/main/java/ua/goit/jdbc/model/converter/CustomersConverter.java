package ua.goit.jdbc.model.converter;

import ua.goit.jdbc.model.dao.CustomersDao;
import ua.goit.jdbc.model.dto.CustomersDto;

public class CustomersConverter {
    public CustomersDto convert(CustomersDao customersDao) {
        CustomersDto dto = new CustomersDto();
        dto.setId(customersDao.getId());
        dto.setName(customersDao.getName());
        dto.setBusiness(customersDao.getBusiness());
        return dto;
    }

    public CustomersDao convert(CustomersDto customersDto) {
        CustomersDao dao = new CustomersDao();
        dao.setId(customersDto.getId());
        dao.setName(customersDto.getName());
        dao.setBusiness(customersDto.getBusiness());
        return dao;
    }
}
