package ua.goit.project.model.converter;

import ua.goit.project.model.dao.CompanyDao;
import ua.goit.project.model.dto.CompanyDto;

public class CompanyConverter implements Convertor<CompanyDao, CompanyDto> {

    @Override
    public CompanyDto toDto(CompanyDao dao) {
        CompanyDto dto = new CompanyDto();
        dto.setId(dao.getId());
        dto.setName(dao.getName());
        dto.setDescription(dao.getDescription());
        dto.setNumberOfEmployees(dao.getNumberOfEmployees());
        return dto;
    }

    @Override
    public CompanyDao toDao(CompanyDto dto) {
        CompanyDao dao = new CompanyDao();
        dao.setId(dto.getId());
        dao.setName(dto.getName());
        dao.setDescription(dto.getDescription());
        dao.setNumberOfEmployees(dto.getNumberOfEmployees());
        return dao;
    }
}
