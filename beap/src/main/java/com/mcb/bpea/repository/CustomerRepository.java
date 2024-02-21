package com.mcb.bpea.repository;

import com.mcb.bpea.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByCustomerNumber(String customerNumber);
    void deleteByCustomerNumber(String customerNumber);
    boolean existsByCustomerNumber(String customerNumber);
}