package com.mcb.bpea.service;

import com.mcb.bpea.entities.Currency;
import com.mcb.bpea.exception.ResourceNotFoundException;
import com.mcb.bpea.repository.CurrencyRepository;
import com.mcb.bpea.service.impl.CurrencyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceImplTest {

    @Mock
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private CurrencyServiceImpl subject;

    

    @Test
    void getAllCurrencies() {
        // Given
        List<Currency> currencies = Arrays.asList(
                new Currency(1L, "Currency1"),
                new Currency(2L, "Currency2")
        );
        when(currencyRepository.findAll()).thenReturn(currencies);

        // When
        List<Currency> result = subject.getAllCurrencies();

        // Then
        assertEquals(currencies, result);
        verify(currencyRepository, times(1)).findAll();
    }

    @Test
    void getCurrencyById() {
        // Given
        Long currencyId = 1L;
        Currency currency = new Currency(currencyId, "Currency1");
        when(currencyRepository.findById(currencyId)).thenReturn(Optional.of(currency));

        // When
        Currency result = subject.getCurrencyById(currencyId);

        // Then
        assertEquals(currency, result);
        verify(currencyRepository, times(1)).findById(currencyId);
    }

    @Test
    void getCurrencyById_NotFound() {
        // Given
        Long currencyId = 1L;
        when(currencyRepository.findById(currencyId)).thenReturn(Optional.empty());

        // When and Assert
        assertThrows(ResourceNotFoundException.class, () -> subject.getCurrencyById(currencyId));
        verify(currencyRepository, times(1)).findById(currencyId);
    }

    @Test
    void createCurrency() {
        // Given
        Currency currencyToCreate = new Currency(null, "NewCurrency");
        Currency createdCurrency = new Currency(1L, "NewCurrency");
        when(currencyRepository.save(currencyToCreate)).thenReturn(createdCurrency);

        // When
        Currency result = subject.createCurrency(currencyToCreate);

        // Then
        assertEquals(createdCurrency, result);
        verify(currencyRepository, times(1)).save(currencyToCreate);
    }

    @Test
    void updateCurrency() {
        // Given
        Long currencyId = 1L;
        Currency existingCurrency = new Currency(currencyId, "NGN");
        Currency updatedCurrency = new Currency(currencyId, "MUR");

        when(currencyRepository.findById(currencyId)).thenReturn(Optional.of(existingCurrency));
        when(currencyRepository.save(updatedCurrency)).thenReturn(updatedCurrency);

        // When
        Currency result = subject.updateCurrency(currencyId, updatedCurrency);

        // Then
        assertEquals(updatedCurrency, result);
        verify(currencyRepository, times(1)).findById(currencyId);
        verify(currencyRepository, times(1)).save(updatedCurrency);
    }

    @Test
    void updateCurrency_NotFound() {
        // Given
        Long currencyId = 1L;
        Currency updatedCurrency = new Currency(currencyId, "MUR");

        when(currencyRepository.findById(currencyId)).thenReturn(Optional.empty());

        // When and Assert
        assertThrows(ResourceNotFoundException.class, () -> subject.updateCurrency(currencyId, updatedCurrency));
        verify(currencyRepository, times(1)).findById(currencyId);
        verify(currencyRepository, never()).save(updatedCurrency);
    }

    @Test
    void deleteCurrency() {
        // Given
        Long currencyId = 1L;

        // When
        subject.deleteCurrency(currencyId);

        // Then
        verify(currencyRepository, times(1)).deleteById(currencyId);
    }
}
