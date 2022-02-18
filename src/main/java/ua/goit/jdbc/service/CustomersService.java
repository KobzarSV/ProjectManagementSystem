package ua.goit.jdbc.service;

import ua.goit.jdbc.dataLayer.CustomerRepository;
import ua.goit.jdbc.model.converter.CustomersConverter;
import ua.goit.jdbc.model.dto.CustomersDto;

import java.util.List;
import java.util.stream.Collectors;

public class CustomersService {
    private final CustomersConverter converter;
    private final CustomerRepository customerRepository;

    public CustomersService(CustomersConverter converter, CustomerRepository customerRepository) {
        this.converter = converter;
        this.customerRepository = customerRepository;
    }

    public CustomersDto read(int id) {
        return converter.convert(customerRepository.readById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Customer with id %s not found", id))));
    }

    public List<CustomersDto> read() {
        return customerRepository.readAll().stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public void create(CustomersDto dto) {
        customerRepository.readById(dto.getId()).ifPresent(customer ->
        {
            throw new IllegalArgumentException(String.format("Customer with id %s already exist", customer.getId()));
        });
        customerRepository.create(converter.convert(dto));
    }

    public int update(CustomersDto dto) {
        return customerRepository.update(converter.convert(dto));
    }

    public void delete(CustomersDto dto) {
        customerRepository.delete(converter.convert(dto));
    }
}
