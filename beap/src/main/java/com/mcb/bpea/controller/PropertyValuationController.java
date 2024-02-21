package com.mcb.bpea.controller;

import com.mcb.bpea.dto.PropertyValuationDto;
import com.mcb.bpea.dto.ValuationProjection;
import com.mcb.bpea.service.PropertyValuationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/property-valuations")
@RequiredArgsConstructor
public class PropertyValuationController {

    private final PropertyValuationService propertyValuationService;

    @PostMapping
    public PropertyValuationDto savePropertyValuation(@RequestBody PropertyValuationDto propertyValuation) {
        return propertyValuationService.savePropertyValuation(propertyValuation);
    }

    @GetMapping("/facility/{facilityType}")
    public PropertyValuationDto getPropertyValuationByType(@PathVariable String facilityType) {
        return propertyValuationService.getPropertyValuationByType(facilityType);
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
