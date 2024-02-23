package com.mcb.app.controller;


import com.mcb.app.service.PropertyValuationService;
import com.mcb.commons.dto.PropertyValuationDto;
import com.mcb.commons.enums.EvaluationType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/property-valuations")
@RequiredArgsConstructor
public class PropertyValuationController {

    private final PropertyValuationService propertyValuationService;

    @PostMapping
    public PropertyValuationDto savePropertyValuation(@RequestBody @Valid PropertyValuationDto propertyValuation) {
        return propertyValuationService.savePropertyValuation(propertyValuation);
    }

    @GetMapping("/facility/{facilityType}")
    public PropertyValuationDto getPropertyValuationByType(@PathVariable EvaluationType propertyValuation) {
        return propertyValuationService.getPropertyValuationByType(propertyValuation);
    }

    @GetMapping("/{id}")
    public PropertyValuationDto getPropertyValuationById(@PathVariable Long id) {
        return propertyValuationService.getPropertyValuationById(id);
    }
    @GetMapping
    public List<PropertyValuationDto> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return propertyValuationService.getAllPropertyValuations(page, size);
    }

    @DeleteMapping("/{id}")
    public void deletePropertyValuation(@PathVariable Long id) {
        propertyValuationService.deletePropertyValuationById(id);
    }
}
