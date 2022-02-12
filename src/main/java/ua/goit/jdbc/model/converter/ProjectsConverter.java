package ua.goit.jdbc.model.converter;

import ua.goit.jdbc.model.dao.ProjectsDao;
import ua.goit.jdbc.model.dto.ProjectsDto;

public class ProjectsConverter {

    public ProjectsDto convert(ProjectsDao projectsDao) {
        ProjectsDto dto = new ProjectsDto();
        dto.setId(projectsDao.getId());
        dto.setName(projectsDao.getName());
        dto.setDescription(projectsDao.getDescription());
        dto.setCompanyId(projectsDao.getCompanyId());
        dto.setCustomerId(projectsDao.getCustomerId());
        return dto;
    }

    public ProjectsDao convert(ProjectsDto projectsDto) {
        ProjectsDao dao = new ProjectsDao();
        dao.setId(projectsDto.getId());
        dao.setName(projectsDto.getName());
        dao.setDescription(projectsDto.getDescription());
        dao.setCompanyId(projectsDto.getCompanyId());
        dao.setCustomerId(projectsDto.getCustomerId());
        return dao;
    }
}
