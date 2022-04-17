package ua.goit.project.service;

import ua.goit.project.dataLayer.ProjectsRepository;
import ua.goit.project.model.converter.ProjectsConverter;
import ua.goit.project.model.dto.ProjectsDto;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectsService {
    private final ProjectsConverter converter;
    private final ProjectsRepository projectsRepository;

    public ProjectsService(ProjectsConverter converter, ProjectsRepository projectsRepository) {
        this.converter = converter;
        this.projectsRepository = projectsRepository;
    }

    public ProjectsDto find(int id) {
        return converter.toDto(projectsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Project with id %s not found", id))));
    }

    public List<ProjectsDto> find() {
        return projectsRepository.findAll().stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    public void create(ProjectsDto dto) {
        projectsRepository.findById(dto.getId()).ifPresent(developer ->
        {
            throw new IllegalArgumentException(String.format("Developer with id %s already exist", developer.getId()));
        });
        projectsRepository.create(converter.toDao(dto));
    }

    public int update(ProjectsDto dto) {
        return projectsRepository.update(converter.toDao(dto));
    }

    public void delete(ProjectsDto dto) {
        projectsRepository.delete(converter.toDao(dto));
    }

    public List<ProjectsDto> projectsDateCountDevDto() {
        return projectsRepository.getProjectsDateAndCountDev().stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }
}
