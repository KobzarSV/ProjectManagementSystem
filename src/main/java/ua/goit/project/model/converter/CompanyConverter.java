package ua.goit.project.model.converter;

import ua.goit.project.model.dao.CompaniesDao;
import ua.goit.project.model.dto.CompaniesDto;

public class CompanyConverter implements Convertor<CompaniesDao, CompaniesDto> {

    private DevelopersConverter developersConverter;
    private ProjectsConverter projectsConverter;

    public CompanyConverter(DevelopersConverter developersConverter, ProjectsConverter projectsConverter) {
        this.developersConverter = developersConverter;
        this.projectsConverter = projectsConverter;
    }

    @Override
    public CompaniesDto toDto(CompaniesDao dao) {
        CompaniesDto dto = new CompaniesDto();
        dto.setId(dao.getId());
        dto.setName(dao.getName());
        dto.setDescription(dao.getDescription());
        dto.setEmployees(dao.getEmployees());
        return dto;
    }

    @Override
    public CompaniesDao toDao(CompaniesDto dto) {
        CompaniesDao dao = new CompaniesDao();
        dao.setId(dto.getId());
        dao.setName(dto.getName());
        dao.setDescription(dto.getDescription());
        dao.setEmployees(dto.getEmployees());
        return dao;
    }
}
