package ua.goit.project.service;

import ua.goit.project.dataLayer.DevelopersRepository;
import ua.goit.project.model.converter.DevelopersConverter;
import ua.goit.project.model.dto.DevelopersDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DevelopersService {
    private final DevelopersRepository developersRepository;
    private final DevelopersConverter developersConverter;

    public DevelopersService(DevelopersRepository developersRepository, DevelopersConverter developersConverter) {
        this.developersRepository = developersRepository;
        this.developersConverter = developersConverter;
    }

    public DevelopersDto find(int id) {
        return developersConverter.toDto(developersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Developer with id %s not found", id))));
    }

    public List<DevelopersDto> find() {
        return developersRepository.findAll().stream()
                .map(developersConverter::toDto)
                .collect(Collectors.toList());
    }

    public void create(DevelopersDto dto) {
        Integer developerId = developersRepository.create(developersConverter.toDao(dto));
        dto.setId(developerId);
    }

    public void update(DevelopersDto dto) {
        developersRepository.update(developersConverter.toDao(dto));
    }

    public void delete(DevelopersDto dto) {
        developersRepository.delete(developersConverter.toDao(dto));
    }

    public Set<DevelopersDto> findByIds(Set<Integer> developerIds) {
        return developersRepository.findByIds(developerIds)
                .stream()
                .map(developersConverter::toDto)
                .collect(Collectors.toSet());
    }
}
