package ua.goit.jdbc.service;

import ua.goit.jdbc.dataLayer.DevelopersRepository;
import ua.goit.jdbc.model.converter.DevelopersConverter;
import ua.goit.jdbc.model.dto.DevelopersDto;

import java.util.List;
import java.util.stream.Collectors;

public class DevelopersService {
    private final DevelopersConverter converter;
    private final DevelopersRepository developersRepository;

    public DevelopersService(DevelopersConverter converter, DevelopersRepository developersRepository) {
        this.converter = converter;
        this.developersRepository = developersRepository;
    }

    public DevelopersDto read(int id) {
        return converter.convert(developersRepository.readById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Developer with id %s not found", id))));
    }

    public List<DevelopersDto> read() {
        return developersRepository.readAll().stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public void create(DevelopersDto dto) {
        developersRepository.readById(dto.getId()).ifPresent(developer ->
        {
            throw new IllegalArgumentException(String.format("Developer with id %s already exist", developer.getId()));
        });
        developersRepository.create(converter.convert(dto));
    }

    public int update(DevelopersDto dto) {
        return developersRepository.update(converter.convert(dto));
    }

    public void delete(DevelopersDto dto) {
        developersRepository.delete(converter.convert(dto));
    }

    public List<DevelopersDto> developersOfProjectById(int id) {
        return developersRepository.getDevOfProjectById(id).stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public List<DevelopersDto> developersOfProjectByName(String nameProject) {
        return developersRepository.getDevOfProjectByName(nameProject).stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public List<DevelopersDto> developersByIndustry(String industry) {
        return developersRepository.getAllDevelopersByIndustry(industry).stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public List<DevelopersDto> developersBySkillLevel(String skillLevel) {
        return developersRepository.getAllDevelopersBySkillLevel(skillLevel).stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }
}
