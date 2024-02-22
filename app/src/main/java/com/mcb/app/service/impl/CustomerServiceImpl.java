package com.mcb.app.service.impl;

import com.mcb.app.converter.CustomerConverter;
import com.mcb.app.repository.CustomerRepository;
import com.mcb.app.service.CustomerService;
import com.mcb.commons.dto.CustomerDto;
import com.mcb.commons.entities.Customer;
import com.mcb.commons.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerConverter customerConverter;

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer savedCustomer = customerRepository.save(customerConverter.toCustomer(customerDto));
        return customerConverter.toCustomerDto(savedCustomer);
    }

    @Override
    public CustomerDto getCustomerByNumber(String customerNumber) {
        return customerRepository.findByCustomerNumber(customerNumber)
                .map(customerConverter::toCustomerDto)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with customerNumber: " + customerNumber));
    }

    @Override
    public List<CustomerDto> getAllCustomers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Customer> customerPage = customerRepository.findAll(pageable);
        return customerPage.getContent()
                .stream()
                .map(customerConverter::toCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCustomer(String customerNumber) {
        if (!customerRepository.existsByCustomerNumber(customerNumber)) {
            throw new ResourceNotFoundException("Customer not found with customerNumber: " + customerNumber);
        }

        customerRepository.deleteByCustomerNumber(customerNumber);
    }

}
