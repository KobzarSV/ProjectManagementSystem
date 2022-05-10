package ua.goit.project.model.converter;

import ua.goit.project.model.dao.DevelopersProjectsDao;
import ua.goit.project.model.dto.DevelopersProjectsDto;

public class DevelopersProjectsConverter implements Convertor<DevelopersProjectsDao, DevelopersProjectsDto> {

    @Override
    public DevelopersProjectsDto toDto(DevelopersProjectsDao dao) {
        DevelopersProjectsDto dto = new DevelopersProjectsDto();
        dto.setDeveloperId(dao.getDeveloperId());
        dto.setProjectId(dao.getProjectId());
        return dto;
    }

    @Override
    public DevelopersProjectsDao toDao(DevelopersProjectsDto dto) {
        DevelopersProjectsDao dao = new DevelopersProjectsDao();
        dao.setDeveloperId(dto.getDeveloperId());
        dao.setProjectId(dto.getProjectId());
        return dao;
    }
}
