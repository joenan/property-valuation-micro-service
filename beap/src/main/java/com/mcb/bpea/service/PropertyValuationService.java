package com.mcb.bpea.service;

import com.mcb.bpea.dto.PropertyValuationDto;
import com.mcb.bpea.dto.ValuationProjection;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface PropertyValuationService {
    PropertyValuationDto savePropertyValuation(PropertyValuationDto propertyValuation);
    PropertyValuationDto getPropertyValuationByType(String facilityType);
    PropertyValuationDto getPropertyValuationById(Long id);
    List<PropertyValuationDto> getAllPropertyValuations(int page, int size);
    void deletePropertyValuationById(Long id);
}