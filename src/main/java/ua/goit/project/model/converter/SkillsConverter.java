package ua.goit.project.model.converter;

import ua.goit.project.model.dao.SkillsDao;
import ua.goit.project.model.dto.SkillsDto;

public class SkillsConverter implements Convertor<SkillsDao, SkillsDto> {

    @Override
    public SkillsDto toDto(SkillsDao dao) {
        SkillsDto dto = new SkillsDto();
        dto.setId(dao.getId());
        dto.setIndustry(dao.getIndustry());
        dto.setSkillLevel(dao.getSkillLevel());
        return dto;
    }

    @Override
    public SkillsDao toDao(SkillsDto dto) {
        SkillsDao dao = new SkillsDao();
        dao.setId(dto.getId());
        dao.setIndustry(dto.getIndustry());
        dao.setSkillLevel(dto.getSkillLevel());
        return dao;
    }
}
