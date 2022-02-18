package ua.goit.jdbc.service;

import ua.goit.jdbc.dataLayer.ProjectsRepository;
import ua.goit.jdbc.model.converter.ProjectsConverter;
import ua.goit.jdbc.model.dto.ProjectsDto;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectsService {
    private final ProjectsConverter converter;
    private final ProjectsRepository projectsRepository;

    public ProjectsService(ProjectsConverter converter, ProjectsRepository projectsRepository) {
        this.converter = converter;
        this.projectsRepository = projectsRepository;
    }

    public ProjectsDto read(int id) {
        return converter.convert(projectsRepository.readById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Project with id %s not found", id))));
    }

    public List<ProjectsDto> read() {
        return projectsRepository.readAll().stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public void create(ProjectsDto dto) {
        projectsRepository.readById(dto.getId()).ifPresent(developer ->
        {
            throw new IllegalArgumentException(String.format("Developer with id %s already exist", developer.getId()));
        });
        projectsRepository.create(converter.convert(dto));
    }

    public int update(ProjectsDto dto) {
        return projectsRepository.update(converter.convert(dto));
    }

    public void delete(ProjectsDto dto) {
        projectsRepository.delete(converter.convert(dto));
    }

    public List<ProjectsDto> projectsDateCountDevDto() {
        return projectsRepository.getProjectsDateAndCountDev().stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }
}
