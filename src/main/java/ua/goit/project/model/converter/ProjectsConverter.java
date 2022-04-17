package ua.goit.project.model.converter;

import ua.goit.project.model.dao.ProjectsDao;
import ua.goit.project.model.dto.ProjectsDto;

public class ProjectsConverter implements Convertor<ProjectsDao, ProjectsDto> {

    @Override
    public ProjectsDto toDto(ProjectsDao dao) {
        ProjectsDto dto = new ProjectsDto();
        dto.setId(dao.getId());
        dto.setName(dao.getName());
        dto.setDescription(dao.getDescription());
        dto.setCompanyId(dao.getCompanyId());
        dto.setCustomerId(dao.getCustomerId());
        dto.setDate(dao.getDate());
        dto.setCountDevelopers(dao.getCountDevelopers());
        dto.setCompanyName(dao.getCompanyName());
        dto.setCustomerName(dao.getCustomerName());
        return dto;
    }

    @Override
    public ProjectsDao toDao(ProjectsDto dto) {
        ProjectsDao dao = new ProjectsDao();
        dao.setId(dto.getId());
        dao.setName(dto.getName());
        dao.setDescription(dto.getDescription());
        dao.setCompanyId(dto.getCompanyId());
        dao.setCustomerId(dto.getCustomerId());
        dao.setDate(dto.getDate());
        dao.setCountDevelopers(dto.getCountDevelopers());
        dao.setCompanyName(dto.getCompanyName());
        dao.setCustomerName(dto.getCustomerName());
        return dao;
    }
}
