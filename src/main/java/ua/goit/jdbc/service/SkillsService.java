package ua.goit.jdbc.service;

import ua.goit.jdbc.dataLayer.SkillsRepository;
import ua.goit.jdbc.model.converter.SkillsConverter;
import ua.goit.jdbc.model.dto.SkillsDto;

public class SkillsService {
    private final SkillsConverter converter;
    private final SkillsRepository skillsRepository;

    public SkillsService(SkillsConverter converter, SkillsRepository skillsRepository) {
        this.converter = converter;
        this.skillsRepository = skillsRepository;
    }

    public void createDeveloperSkills(SkillsDto skillsDto) {
        skillsRepository.createDeveloperSkills(converter.convert(skillsDto));
    }
}
