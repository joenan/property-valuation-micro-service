package com.mcb.bpea.service;

import com.mcb.bpea.dto.CustomerDto;
import com.mcb.bpea.entities.Customer;

import java.util.List;

public interface CustomerService {
    CustomerDto saveCustomer(CustomerDto customer);
    CustomerDto getCustomerByNumber(String customerNumber);
    void deleteCustomer(String customerNumber);

    List<CustomerDto> getAllCustomers(int page, int size);
}