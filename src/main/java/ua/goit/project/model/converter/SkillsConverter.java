package ua.goit.project.model.converter;

import ua.goit.project.model.dao.SkillsDao;
import ua.goit.project.model.dto.SkillsDto;

import java.util.Objects;

public class SkillsConverter implements Convertor<SkillsDao, SkillsDto> {

    @Override
    public SkillsDto toDto(SkillsDao dao) {
        SkillsDto skillsDto = new SkillsDto();
        skillsDto.setDevelopersId(skillsDto.getDevelopersId());
        if (dao.getSkillsDeveloperId() == 11) {
            skillsDto.setIndustry("Java");
            skillsDto.setSkillLevel("Junior");
        }
        if (dao.getSkillsDeveloperId() == 12) {
            skillsDto.setIndustry("Java");
            skillsDto.setSkillLevel("Middle");
        }
        if (dao.getSkillsDeveloperId() == 13) {
            skillsDto.setIndustry("Java");
            skillsDto.setSkillLevel("Senior");
        }
        if (dao.getSkillsDeveloperId() == 21) {
            skillsDto.setIndustry("C++");
            skillsDto.setSkillLevel("Junior");
        }
        if (dao.getSkillsDeveloperId() == 22) {
            skillsDto.setIndustry("C++");
            skillsDto.setSkillLevel("Middle");
        }
        if (dao.getSkillsDeveloperId() == 23) {
            skillsDto.setIndustry("C++");
            skillsDto.setSkillLevel("Senior");
        }
        if (dao.getSkillsDeveloperId() == 31) {
            skillsDto.setIndustry("C#");
            skillsDto.setSkillLevel("Junior");
        }
        if (dao.getSkillsDeveloperId() == 32) {
            skillsDto.setIndustry("C#");
            skillsDto.setSkillLevel("Middle");
        }
        if (dao.getSkillsDeveloperId() == 33) {
            skillsDto.setIndustry("C#");
            skillsDto.setSkillLevel("Senior");
        }
        if (dao.getSkillsDeveloperId() == 41) {
            skillsDto.setIndustry("JS");
            skillsDto.setSkillLevel("Junior");
        }
        if (dao.getSkillsDeveloperId() == 42) {
            skillsDto.setIndustry("JS");
            skillsDto.setSkillLevel("Middle");
        }
        if (dao.getSkillsDeveloperId() == 43) {
            skillsDto.setIndustry("JS");
            skillsDto.setSkillLevel("Senior");
        }
        return skillsDto;
    }

    @Override
    public SkillsDao toDao(SkillsDto dto) {
        SkillsDao skillsDao = new SkillsDao();
        if (Objects.equals(dto.getIndustry(), "Java") & Objects.equals(dto.getSkillLevel(), "Junior")) {
            skillsDao.setDeveloperSkillsId(dto.getDevelopersId());
            skillsDao.setSkillsDeveloperId(11);
        }
        if (Objects.equals(dto.getIndustry(), "Java") & Objects.equals(dto.getSkillLevel(), "Middle")) {
            skillsDao.setDeveloperSkillsId(dto.getDevelopersId());
            skillsDao.setSkillsDeveloperId(12);
        }
        if (Objects.equals(dto.getIndustry(), "Java") & Objects.equals(dto.getSkillLevel(), "Senior")) {
            skillsDao.setDeveloperSkillsId(dto.getDevelopersId());
            skillsDao.setSkillsDeveloperId(13);
        }
        if (Objects.equals(dto.getIndustry(), "C++") & Objects.equals(dto.getSkillLevel(), "Junior")) {
            skillsDao.setDeveloperSkillsId(dto.getDevelopersId());
            skillsDao.setSkillsDeveloperId(21);
        }
        if (Objects.equals(dto.getIndustry(), "C++") & Objects.equals(dto.getSkillLevel(), "Middle")) {
            skillsDao.setDeveloperSkillsId(dto.getDevelopersId());
            skillsDao.setSkillsDeveloperId(22);
        }
        if (Objects.equals(dto.getIndustry(), "C++") & Objects.equals(dto.getSkillLevel(), "Senior")) {
            skillsDao.setDeveloperSkillsId(dto.getDevelopersId());
            skillsDao.setSkillsDeveloperId(23);
        }
        if (Objects.equals(dto.getIndustry(), "C#") & Objects.equals(dto.getSkillLevel(), "Junior")) {
            skillsDao.setDeveloperSkillsId(dto.getDevelopersId());
            skillsDao.setSkillsDeveloperId(31);
        }
        if (Objects.equals(dto.getIndustry(), "C#") & Objects.equals(dto.getSkillLevel(), "Middle")) {
            skillsDao.setDeveloperSkillsId(dto.getDevelopersId());
            skillsDao.setSkillsDeveloperId(32);
        }
        if (Objects.equals(dto.getIndustry(), "C#") & Objects.equals(dto.getSkillLevel(), "Senior")) {
            skillsDao.setDeveloperSkillsId(dto.getDevelopersId());
            skillsDao.setSkillsDeveloperId(33);
        }
        if (Objects.equals(dto.getIndustry(), "JS") & Objects.equals(dto.getSkillLevel(), "Junior")) {
            skillsDao.setDeveloperSkillsId(dto.getDevelopersId());
            skillsDao.setSkillsDeveloperId(41);
        }
        if (Objects.equals(dto.getIndustry(), "JS") & Objects.equals(dto.getSkillLevel(), "Middle")) {
            skillsDao.setDeveloperSkillsId(dto.getDevelopersId());
            skillsDao.setSkillsDeveloperId(42);
        }
        if (Objects.equals(dto.getIndustry(), "JS") & Objects.equals(dto.getSkillLevel(), "Senior")) {
            skillsDao.setDeveloperSkillsId(dto.getDevelopersId());
            skillsDao.setSkillsDeveloperId(43);
        }
        return skillsDao;
    }
}
