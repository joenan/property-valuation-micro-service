package com.mcb.app.service;



import com.mcb.commons.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto saveCustomer(CustomerDto customer);
    CustomerDto getCustomerByNumber(String customerNumber);
    void deleteCustomer(String customerNumber);

    List<CustomerDto> getAllCustomers(int page, int size);
}