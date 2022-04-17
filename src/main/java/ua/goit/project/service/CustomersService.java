package ua.goit.project.service;

import ua.goit.project.dataLayer.CustomerRepository;
import ua.goit.project.model.converter.CustomersConverter;
import ua.goit.project.model.dto.CustomersDto;

import java.util.List;
import java.util.stream.Collectors;

public class CustomersService {
    private final CustomersConverter converter;
    private final CustomerRepository customerRepository;

    public CustomersService(CustomersConverter converter, CustomerRepository customerRepository) {
        this.converter = converter;
        this.customerRepository = customerRepository;
    }

    public CustomersDto find(int id) {
        return converter.toDto(customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Customer with id %s not found", id))));
    }

    public List<CustomersDto> find() {
        return customerRepository.findAll().stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    public void create(CustomersDto dto) {
        customerRepository.findById(dto.getId()).ifPresent(customer ->
        {
            throw new IllegalArgumentException(String.format("Customer with id %s already exist", customer.getId()));
        });
        customerRepository.create(converter.toDao(dto));
    }

    public int update(CustomersDto dto) {
        return customerRepository.update(converter.toDao(dto));
    }

    public void delete(CustomersDto dto) {
        customerRepository.delete(converter.toDao(dto));
    }
}
