package ua.goit.project.service;

import ua.goit.project.dataLayer.CompanyRepository;
import ua.goit.project.model.converter.CompanyConverter;
import ua.goit.project.model.dto.CompanyDto;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyService {

    private final CompanyConverter converter;
    private final CompanyRepository repository;

    public CompanyService(CompanyConverter converter, CompanyRepository repository) {
        this.converter = converter;
        this.repository = repository;
    }

    public CompanyDto find(int id) {
        return converter.toDto(repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Company with id %s not found", id))));
    }

    public List<CompanyDto> find() {
        return repository.findAll().stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    public void create(CompanyDto dto) {
        repository.findById(dto.getId()).ifPresent(company ->
        {
            throw new IllegalArgumentException(String.format("Company with id %s already exist", company.getId()));
        });
        repository.create(converter.toDao(dto));
    }

    public int update(CompanyDto dto) {
        return repository.update(converter.toDao(dto));
    }

    public void delete(CompanyDto dto) {
        repository.delete(converter.toDao(dto));
    }
}
