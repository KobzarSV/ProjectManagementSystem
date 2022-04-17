package ua.goit.project.service;

import ua.goit.project.dataLayer.SkillsRepository;
import ua.goit.project.model.converter.SkillsConverter;
import ua.goit.project.model.dto.SkillsDto;

public class SkillsService {
    private final SkillsConverter converter;
    private final SkillsRepository skillsRepository;

    public SkillsService(SkillsConverter converter, SkillsRepository skillsRepository) {
        this.converter = converter;
        this.skillsRepository = skillsRepository;
    }

    public void createDeveloperSkills(SkillsDto skillsDto) {
        skillsRepository.createDeveloperSkills(converter.toDao(skillsDto));
    }
}
