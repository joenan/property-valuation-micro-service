package com.mcb.bpea.controller;

import com.mcb.bpea.dto.CustomerDto;
import com.mcb.bpea.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public CustomerDto saveCustomer(@RequestBody CustomerDto customer) {
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/{customerNumber}")
    public CustomerDto getCustomerByNumber(@PathVariable String customerNumber) {
        return customerService.getCustomerByNumber(customerNumber);
    }

    @GetMapping
    public List<CustomerDto> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return customerService.getAllCustomers(page, size);
    }


    @DeleteMapping("/{customerNumber}")
    public void deleteCustomer(@PathVariable String customerNumber) {
        customerService.deleteCustomer(customerNumber);
    }
}