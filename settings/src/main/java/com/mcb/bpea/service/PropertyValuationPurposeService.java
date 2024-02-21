package com.mcb.bpea.service;

import com.mcb.bpea.entities.PropertyValuationPurpose;

import java.util.List;

public interface PropertyValuationPurposeService {
    List<PropertyValuationPurpose> getAllPurposes();
    PropertyValuationPurpose getPurposeById(Long id);
    PropertyValuationPurpose createPurpose(PropertyValuationPurpose purpose);
    PropertyValuationPurpose updatePurpose(Long id, PropertyValuationPurpose purpose);
    void deletePurpose(Long id);
}
