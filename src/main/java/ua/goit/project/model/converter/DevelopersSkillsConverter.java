package ua.goit.project.model.converter;

import ua.goit.project.model.dao.DevelopersSkillsDao;
import ua.goit.project.model.dto.DevelopersSkillsDto;

public class DevelopersSkillsConverter implements Convertor<DevelopersSkillsDao, DevelopersSkillsDto> {

    @Override
    public DevelopersSkillsDto toDto(DevelopersSkillsDao dao) {
        DevelopersSkillsDto dto = new DevelopersSkillsDto();
        dto.setDeveloperId(dao.getDeveloperId());
        dto.setSkillId(dao.getSkillId());
        return dto;
    }

    @Override
    public DevelopersSkillsDao toDao(DevelopersSkillsDto dto) {
        DevelopersSkillsDao dao = new DevelopersSkillsDao();
        dao.setDeveloperId(dto.getDeveloperId());
        dao.setSkillId(dto.getSkillId());
        return dao;
    }
}
