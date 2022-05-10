package ua.goit.project.service;

import ua.goit.project.dataLayer.ProjectsRepository;
import ua.goit.project.model.converter.DevelopersConverter;
import ua.goit.project.model.converter.ProjectsConverter;
import ua.goit.project.model.dto.ProjectsDto;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectsService {
    private final ProjectsRepository projectsRepository;
    private final ProjectsConverter projectsConverter;
    private final DevelopersConverter developersConverter;

    public ProjectsService(ProjectsRepository projectsRepository, ProjectsConverter projectsConverter, DevelopersConverter developersConverter) {
        this.projectsRepository = projectsRepository;
        this.projectsConverter = projectsConverter;
        this.developersConverter = developersConverter;
    }

    public ProjectsDto find(int id) {
        return projectsConverter.toDto(projectsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Project with id %s not found", id))));
    }

    public List<ProjectsDto> find() {
        return projectsRepository.findAll().stream()
                .map(projectsConverter::toDto)
                .collect(Collectors.toList());
    }

    public void create(ProjectsDto dto) {
        projectsRepository.create(projectsConverter.toDao(dto));
    }

    public void update(ProjectsDto dto) {
        projectsRepository.update(projectsConverter.toDao(dto));
    }

    public void delete(ProjectsDto dto) {
        projectsRepository.delete(projectsConverter.toDao(dto));
    }
}
