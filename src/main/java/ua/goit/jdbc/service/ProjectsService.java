package ua.goit.jdbc.service;

import ua.goit.jdbc.dataLayer.Repository;
import ua.goit.jdbc.model.converter.ProjectsConverter;
import ua.goit.jdbc.model.dao.ProjectsDao;
import ua.goit.jdbc.model.dto.ProjectsDto;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectsService {
    private final ProjectsConverter converter;
    private final Repository<ProjectsDao> repository;

    public ProjectsService(ProjectsConverter converter, Repository<ProjectsDao> repository) {
        this.converter = converter;
        this.repository = repository;
    }

    public ProjectsDto read(int id) {
        return converter.convert(repository.readById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Project with id %s not found", id))));
    }

    public List<ProjectsDto> read() {
        return repository.readAll().stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public void create(ProjectsDto dto) {
        repository.readById(dto.getId()).ifPresent(developer ->
        {
            throw new IllegalArgumentException(String.format("Developer with id %s already exist", developer.getId()));
        });
        repository.create(converter.convert(dto));
    }

    public int update(ProjectsDto dto) {
        return repository.update(converter.convert(dto));
    }

    public void delete(ProjectsDto dto) {
        repository.delete(converter.convert(dto));
    }
}
