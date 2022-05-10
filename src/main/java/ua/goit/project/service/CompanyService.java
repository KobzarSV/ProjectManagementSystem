package ua.goit.project.service;

import ua.goit.project.dataLayer.CompanyRepository;
import ua.goit.project.model.converter.CompanyConverter;
import ua.goit.project.model.converter.DevelopersConverter;
import ua.goit.project.model.converter.ProjectsConverter;
import ua.goit.project.model.dto.CompaniesDto;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyConverter companyConverter;
    private final DevelopersConverter developersConverter;
    private final ProjectsConverter projectsConverter;

    public CompanyService(CompanyRepository companyRepository, CompanyConverter companyConverter,
                          DevelopersConverter developersConverter, ProjectsConverter projectsConverter) {
        this.companyRepository = companyRepository;
        this.companyConverter = companyConverter;
        this.developersConverter = developersConverter;
        this.projectsConverter = projectsConverter;
    }

    public CompaniesDto find(int id) {
        return companyConverter.toDto(companyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Company with id %s not found", id))));
    }

    public List<CompaniesDto> find() {
        return companyRepository.findAll().stream()
                .map(companyConverter::toDto)
                .collect(Collectors.toList());
    }

    public void create(CompaniesDto dto) {
        companyRepository.create(companyConverter.toDao(dto));
    }

    public void update(CompaniesDto dto) {
        companyRepository.update(companyConverter.toDao(dto));
    }

    public void delete(CompaniesDto dto) {
        companyRepository.delete(companyConverter.toDao(dto));
    }
}
