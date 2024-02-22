package com.mcb.app.service.impl;


import com.mcb.app.repository.CurrencyRepository;
import com.mcb.app.service.CurrencyService;
import com.mcb.commons.entities.Currency;
import com.mcb.commons.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    @Override
    public Currency getCurrencyById(Long id) {
        return currencyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Currency not found with id: " + id));
    }

    @Override
    public Currency createCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }

    @Override
    public Currency updateCurrency(Long id, Currency currency) {
        return currencyRepository.findById(id)
                .map(existingCurrency -> {
                    currency.setId(id);
                    return currencyRepository.save(currency);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Currency not found with id: " + id));
    }

    @Override
    public void deleteCurrency(Long id) {
        currencyRepository.deleteById(id);
    }
}
