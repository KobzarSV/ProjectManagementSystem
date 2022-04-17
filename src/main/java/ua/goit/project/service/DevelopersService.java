package ua.goit.project.service;

import ua.goit.project.dataLayer.DevelopersRepository;
import ua.goit.project.model.converter.DevelopersConverter;
import ua.goit.project.model.dto.DevelopersDto;

import java.util.List;
import java.util.stream.Collectors;

public class DevelopersService {
    private final DevelopersConverter converter;
    private final DevelopersRepository developersRepository;

    public DevelopersService(DevelopersConverter converter, DevelopersRepository developersRepository) {
        this.converter = converter;
        this.developersRepository = developersRepository;
    }

    public DevelopersDto find(int id) {
        return converter.toDto(developersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Developer with id %s not found", id))));
    }

    public List<DevelopersDto> find() {
        return developersRepository.findAll().stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    public void create(DevelopersDto dto) {
        developersRepository.findById(dto.getId()).ifPresent(developer ->
        {
            throw new IllegalArgumentException(String.format("Developer with id %s already exist", developer.getId()));
        });
        Integer developerId = developersRepository.create(converter.toDao(dto));
        dto.setId(developerId);
    }

    public int update(DevelopersDto dto) {
        return developersRepository.update(converter.toDao(dto));
    }

    public void delete(DevelopersDto dto) {
        developersRepository.delete(converter.toDao(dto));
    }

    public List<DevelopersDto> developersOfProjectById(int id) {
        return developersRepository.getDevOfProjectById(id).stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    public List<DevelopersDto> developersOfProjectByName(String nameProject) {
        return developersRepository.getDevOfProjectByName(nameProject).stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    public List<DevelopersDto> developersByIndustry(String industry) {
        return developersRepository.getAllDevelopersByIndustry(industry).stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    public List<DevelopersDto> developersBySkillLevel(String skillLevel) {
        return developersRepository.getAllDevelopersBySkillLevel(skillLevel).stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }
}
