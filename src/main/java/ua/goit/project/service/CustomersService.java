package ua.goit.project.service;

import ua.goit.project.dataLayer.CustomerRepository;
import ua.goit.project.model.converter.CustomersConverter;
import ua.goit.project.model.dto.CustomersDto;

import java.util.List;
import java.util.stream.Collectors;

public class CustomersService {

    private final CustomerRepository customerRepository;
    private final CustomersConverter customersConverter;

    public CustomersService(CustomerRepository customerRepository, CustomersConverter customersConverter) {
        this.customerRepository = customerRepository;
        this.customersConverter = customersConverter;
    }

    public CustomersDto find(int id) {
        return customersConverter.toDto(customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Customer with id %s not found", id))));
    }

    public List<CustomersDto> find() {
        return customerRepository.findAll().stream()
                .map(customersConverter::toDto)
                .collect(Collectors.toList());
    }

    public void create(CustomersDto dto) {
        customerRepository.create(customersConverter.toDao(dto));
    }

    public void update(CustomersDto dto) {
        customerRepository.update(customersConverter.toDao(dto));
    }

    public void delete(CustomersDto dto) {
        customerRepository.delete(customersConverter.toDao(dto));
    }
}
