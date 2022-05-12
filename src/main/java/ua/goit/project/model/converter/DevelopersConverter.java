package ua.goit.project.model.converter;

import ua.goit.project.model.dao.DevelopersDao;
import ua.goit.project.model.dto.DevelopersDto;

import java.util.stream.Collectors;

public class DevelopersConverter implements Convertor<DevelopersDao, DevelopersDto> {

    private SkillsConverter skillsConverter;

    public DevelopersConverter(SkillsConverter skillsConverter) {
        this.skillsConverter = skillsConverter;
    }

    @Override
    public DevelopersDto toDto(DevelopersDao dao) {
        DevelopersDto dto = new DevelopersDto();
        dto.setId(dao.getId());
        dto.setName(dao.getFirstName() + " " + dao.getLastName());
        dto.setAge(dao.getAge());
        dto.setGender(dao.getGender());
        dto.setMail(dao.getMail());
        dto.setCompanyId(dao.getCompanyId());
        dto.setSalary(dao.getSalary());
        dto.setSkills(dao.getSkills().stream()
                .map(skillsConverter::toDto)
                .collect(Collectors.toSet()));
        return dto;
    }

    @Override
    public DevelopersDao toDao(DevelopersDto dto) {
        DevelopersDao dao = new DevelopersDao();
        dao.setId(dto.getId());
        dao.setFirstName(dto.getName().split("\\s")[0]);
        dao.setLastName(dto.getName().substring(dto.getName().lastIndexOf(" ") + 1));
        dao.setAge(dto.getAge());
        dao.setGender(dto.getGender());
        dao.setMail(dto.getMail());
        dao.setCompanyId(dto.getCompanyId());
        dao.setSalary(dto.getSalary());
        dao.setSkills(dto.getSkills().stream()
                .map(skillsConverter::toDao)
                .collect(Collectors.toSet()));
        return dao;
    }
}
