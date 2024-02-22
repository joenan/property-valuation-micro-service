package com.mcb.app.controller;


import com.mcb.app.service.CurrencyService;
import com.mcb.commons.entities.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currencies")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping
    public List<Currency> getAllCurrencies() {
        return currencyService.getAllCurrencies();
    }

    @GetMapping("/{id}")
    public Currency getCurrencyById(@PathVariable Long id) {
        return currencyService.getCurrencyById(id);
    }

    @PostMapping
    public Currency createCurrency(@RequestBody Currency currency) {
        return currencyService.createCurrency(currency);
    }

    @PutMapping("/{id}")
    public Currency updateCurrency(@PathVariable Long id, @RequestBody Currency currency) {
        return currencyService.updateCurrency(id, currency);
    }

    @DeleteMapping("/{id}")
    public void deleteCurrency(@PathVariable Long id) {
        currencyService.deleteCurrency(id);
    }
}
