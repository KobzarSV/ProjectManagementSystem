package ua.goit.jdbc.model.converter;

import ua.goit.jdbc.model.dao.SkillsDao;
import ua.goit.jdbc.model.dto.SkillsDto;

import java.util.Objects;

public class SkillsConverter {
    public SkillsDto convert(SkillsDao skillsDao) {
        SkillsDto skillsDto = new SkillsDto();
        skillsDto.setDevelopersId(skillsDto.getDevelopersId());
        if (skillsDao.getSkillsDeveloperId() == 11) {
            skillsDto.setIndustry("Java");
            skillsDto.setSkillLevel("Junior");
        }
        if (skillsDao.getSkillsDeveloperId() == 12) {
            skillsDto.setIndustry("Java");
            skillsDto.setSkillLevel("Middle");
        }
        if (skillsDao.getSkillsDeveloperId() == 13) {
            skillsDto.setIndustry("Java");
            skillsDto.setSkillLevel("Senior");
        }
        if (skillsDao.getSkillsDeveloperId() == 21) {
            skillsDto.setIndustry("C++");
            skillsDto.setSkillLevel("Junior");
        }
        if (skillsDao.getSkillsDeveloperId() == 22) {
            skillsDto.setIndustry("C++");
            skillsDto.setSkillLevel("Middle");
        }
        if (skillsDao.getSkillsDeveloperId() == 23) {
            skillsDto.setIndustry("C++");
            skillsDto.setSkillLevel("Senior");
        }
        if (skillsDao.getSkillsDeveloperId() == 31) {
            skillsDto.setIndustry("C#");
            skillsDto.setSkillLevel("Junior");
        }
        if (skillsDao.getSkillsDeveloperId() == 32) {
            skillsDto.setIndustry("C#");
            skillsDto.setSkillLevel("Middle");
        }
        if (skillsDao.getSkillsDeveloperId() == 33) {
            skillsDto.setIndustry("C#");
            skillsDto.setSkillLevel("Senior");
        }
        if (skillsDao.getSkillsDeveloperId() == 41) {
            skillsDto.setIndustry("JS");
            skillsDto.setSkillLevel("Junior");
        }
        if (skillsDao.getSkillsDeveloperId() == 42) {
            skillsDto.setIndustry("JS");
            skillsDto.setSkillLevel("Middle");
        }
        if (skillsDao.getSkillsDeveloperId() == 43) {
            skillsDto.setIndustry("JS");
            skillsDto.setSkillLevel("Senior");
        }
        return skillsDto;
    }

    public SkillsDao convert(SkillsDto skillsDto) {
        SkillsDao skillsDao = new SkillsDao();
        if (Objects.equals(skillsDto.getIndustry(), "Java") & Objects.equals(skillsDto.getSkillLevel(), "Junior")) {
            skillsDao.setDeveloperSkillsId(skillsDto.getDevelopersId());
            skillsDao.setSkillsDeveloperId(11);
        }
        if (Objects.equals(skillsDto.getIndustry(), "Java") & Objects.equals(skillsDto.getSkillLevel(), "Middle")) {
            skillsDao.setDeveloperSkillsId(skillsDto.getDevelopersId());
            skillsDao.setSkillsDeveloperId(12);
        }
        if (Objects.equals(skillsDto.getIndustry(), "Java") & Objects.equals(skillsDto.getSkillLevel(), "Senior")) {
            skillsDao.setDeveloperSkillsId(skillsDto.getDevelopersId());
            skillsDao.setSkillsDeveloperId(13);
        }
        if (Objects.equals(skillsDto.getIndustry(), "C++") & Objects.equals(skillsDto.getSkillLevel(), "Junior")) {
            skillsDao.setDeveloperSkillsId(skillsDto.getDevelopersId());
            skillsDao.setSkillsDeveloperId(21);
        }
        if (Objects.equals(skillsDto.getIndustry(), "C++") & Objects.equals(skillsDto.getSkillLevel(), "Middle")) {
            skillsDao.setDeveloperSkillsId(skillsDto.getDevelopersId());
            skillsDao.setSkillsDeveloperId(22);
        }
        if (Objects.equals(skillsDto.getIndustry(), "C++") & Objects.equals(skillsDto.getSkillLevel(), "Senior")) {
            skillsDao.setDeveloperSkillsId(skillsDto.getDevelopersId());
            skillsDao.setSkillsDeveloperId(23);
        }
        if (Objects.equals(skillsDto.getIndustry(), "C#") & Objects.equals(skillsDto.getSkillLevel(), "Junior")) {
            skillsDao.setDeveloperSkillsId(skillsDto.getDevelopersId());
            skillsDao.setSkillsDeveloperId(31);
        }
        if (Objects.equals(skillsDto.getIndustry(), "C#") & Objects.equals(skillsDto.getSkillLevel(), "Middle")) {
            skillsDao.setDeveloperSkillsId(skillsDto.getDevelopersId());
            skillsDao.setSkillsDeveloperId(32);
        }
        if (Objects.equals(skillsDto.getIndustry(), "C#") & Objects.equals(skillsDto.getSkillLevel(), "Senior")) {
            skillsDao.setDeveloperSkillsId(skillsDto.getDevelopersId());
            skillsDao.setSkillsDeveloperId(33);
        }
        if (Objects.equals(skillsDto.getIndustry(), "JS") & Objects.equals(skillsDto.getSkillLevel(), "Junior")) {
            skillsDao.setDeveloperSkillsId(skillsDto.getDevelopersId());
            skillsDao.setSkillsDeveloperId(41);
        }
        if (Objects.equals(skillsDto.getIndustry(), "JS") & Objects.equals(skillsDto.getSkillLevel(), "Middle")) {
            skillsDao.setDeveloperSkillsId(skillsDto.getDevelopersId());
            skillsDao.setSkillsDeveloperId(42);
        }
        if (Objects.equals(skillsDto.getIndustry(), "JS") & Objects.equals(skillsDto.getSkillLevel(), "Senior")) {
            skillsDao.setDeveloperSkillsId(skillsDto.getDevelopersId());
            skillsDao.setSkillsDeveloperId(43);
        }
        return skillsDao;
    }
}
