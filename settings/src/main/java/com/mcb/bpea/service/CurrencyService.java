package com.mcb.bpea.service;

import com.mcb.bpea.entities.Currency;

import java.util.List;

public interface CurrencyService {
    List<Currency> getAllCurrencies();
    Currency getCurrencyById(Long id);
    Currency createCurrency(Currency currency);
    Currency updateCurrency(Long id, Currency currency);
    void deleteCurrency(Long id);
}
