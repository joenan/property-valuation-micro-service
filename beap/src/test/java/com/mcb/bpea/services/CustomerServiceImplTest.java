package com.mcb.bpea.services;

import com.mcb.bpea.converter.CustomerConverter;
import com.mcb.bpea.dto.CustomerDto;
import com.mcb.bpea.entities.Customer;
import com.mcb.bpea.repository.CustomerRepository;
import com.mcb.bpea.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerConverter customerConverter;

    @InjectMocks
    private CustomerServiceImpl subject;


    @Test
    void saveCustomer() {

        //Given
        CustomerDto customerDto = createSampleCustomerDto();
        Customer customer = createSampleCustomer();

        when(customerConverter.toCustomer(customerDto)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerConverter.toCustomerDto(customer)).thenReturn(customerDto);


        //When
        CustomerDto savedCustomerDto = subject.saveCustomer(customerDto);

        //Then
        assertNotNull(savedCustomerDto);
        assertEquals(customerDto, savedCustomerDto);

        verify(customerConverter, times(1)).toCustomer(customerDto);
        verify(customerRepository, times(1)).save(customer);
        verify(customerConverter, times(1)).toCustomerDto(customer);
    }

    @Test
    void getCustomerByNumber() {

        //Given
        String customerNumber = "123";
        CustomerDto customerDto = createSampleCustomerDto();
        Customer customer = createSampleCustomer();

        when(customerRepository.findByCustomerNumber(customerNumber)).thenReturn(Optional.of(customer));
        when(customerConverter.toCustomerDto(customer)).thenReturn(customerDto);

        //When
        CustomerDto retrievedCustomerDto = subject.getCustomerByNumber(customerNumber);

        //Then
        assertNotNull(retrievedCustomerDto);
        assertEquals(customerDto, retrievedCustomerDto);

        verify(customerRepository, times(1)).findByCustomerNumber(customerNumber);
        verify(customerConverter, times(1)).toCustomerDto(customer);
    }

    @Test
    void getAllCustomers() {

        //Given

        int page = 0;
        int size = 10;
        Customer customer = createSampleCustomer();
        CustomerDto customerDto = createSampleCustomerDto();

        // Mock Page class
        Page<Customer> customerPage = mock(Page.class);

        when(customerRepository.findAll(any(Pageable.class))).thenReturn(customerPage);
        when(customerPage.getContent()).thenReturn(Collections.singletonList(customer));
        when(customerConverter.toCustomerDto(customer)).thenReturn(customerDto);


        //When
        List<CustomerDto> result = subject.getAllCustomers(page, size);

        //Then
        assertEquals(1, result.size());
        assertEquals(customerDto, result.get(0));

        verify(customerRepository, times(1)).findAll(any(Pageable.class));
        verify(customerConverter, times(1)).toCustomerDto(customer);
    }

    @Test
    void deleteCustomer() {

        //Given
        String customerNumber = "123";
        Customer customer = createSampleCustomer();

        when(customerRepository.existsByCustomerNumber(customerNumber)).thenReturn(true);

        //When
        subject.deleteCustomer(customerNumber);

        //Then
        verify(customerRepository, times(1)).existsByCustomerNumber(customerNumber);
        verify(customerRepository, times(1)).deleteByCustomerNumber(customerNumber);
    }

    private CustomerDto createSampleCustomerDto() {
        return CustomerDto.builder()
                .id(1L)
                .customerNumber("123")
                .shortName("Test")
                // Add other fields as needed
                .build();
    }

    private Customer createSampleCustomer() {
        return Customer.builder()
                .id(1L)
                .customerNumber("123")
                .shortName("Test")
                // Add other fields as needed
                .build();
    }
}
