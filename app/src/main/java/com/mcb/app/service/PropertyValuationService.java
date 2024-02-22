package com.mcb.app.service;


import com.mcb.commons.dto.PropertyValuationDto;

import java.util.List;

public interface PropertyValuationService {
    PropertyValuationDto savePropertyValuation(PropertyValuationDto propertyValuation);
    PropertyValuationDto getPropertyValuationByType(String facilityType);
    PropertyValuationDto getPropertyValuationById(Long id);
    List<PropertyValuationDto> getAllPropertyValuations(int page, int size);
    void deletePropertyValuationById(Long id);
}