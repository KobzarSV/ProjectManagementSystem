package ua.goit.project.service;

import ua.goit.project.dataLayer.SkillsRepository;
import ua.goit.project.model.converter.SkillsConverter;
import ua.goit.project.model.dto.SkillsDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SkillsService {

    private final SkillsRepository skillsRepository;
    private final SkillsConverter skillsConverter;

    public SkillsService(SkillsRepository skillsRepository, SkillsConverter skillsConverter) {
        this.skillsRepository = skillsRepository;
        this.skillsConverter = skillsConverter;
    }

    public void createDeveloperSkills(SkillsDto skillsDto) {
        skillsRepository.createDeveloperSkills(skillsConverter.toDao(skillsDto));
    }

    public SkillsDto find(int id) {
        return skillsConverter.toDto(skillsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Skill with id %s not found", id))));

    }

    public List<SkillsDto> find() {
        return skillsRepository.findAll().stream()
                .map(skillsConverter::toDto)
                .collect(Collectors.toList());
    }

    public Set<SkillsDto> findByIds(Set<Integer> skillsIds) {
        return skillsRepository.findByIds(skillsIds)
                .stream()
                .map(skills -> skillsConverter.toDto(skills))
                .collect(Collectors.toSet());
    }
}
