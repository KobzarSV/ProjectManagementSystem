package ua.goit.jdbc.service;

import ua.goit.jdbc.dataLayer.Repository;
import ua.goit.jdbc.model.converter.DevelopersConverter;
import ua.goit.jdbc.model.dao.DevelopersDao;
import ua.goit.jdbc.model.dto.DevelopersDto;

import java.util.List;
import java.util.stream.Collectors;

public class DevelopersService {
    private final DevelopersConverter converter;
    private final Repository<DevelopersDao> repository;

    public DevelopersService(DevelopersConverter converter, Repository<DevelopersDao> repository) {
        this.converter = converter;
        this.repository = repository;
    }

    public DevelopersDto read(int id) {
        return converter.convert(repository.readById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Developer with id %s not found", id))));
    }

    public List<DevelopersDto> read() {
        return repository.readAll().stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public void create(DevelopersDto dto) {
        repository.readById(dto.getId()).ifPresent(developer ->
        {
            throw new IllegalArgumentException(String.format("Developer with id %s already exist", developer.getId()));
        });
        repository.create(converter.convert(dto));
    }

    public int update(DevelopersDto dto) {
        return repository.update(converter.convert(dto));
    }

    public void delete(DevelopersDto dto) {
        repository.delete(converter.convert(dto));
    }
}
