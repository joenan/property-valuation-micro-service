package com.mcb.app.service.impl;


import com.mcb.app.repository.PropertyValuationPurposeRepository;
import com.mcb.app.service.PropertyValuationPurposeService;
import com.mcb.commons.entities.PropertyValuationPurpose;
import com.mcb.commons.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyValuationPurposeServiceImpl implements PropertyValuationPurposeService {

    private final PropertyValuationPurposeRepository purposeRepository;

    @Override
    public List<PropertyValuationPurpose> getAllPurposes() {
        return purposeRepository.findAll();
    }

    @Override
    public PropertyValuationPurpose getPurposeById(Long id) {
        return purposeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purpose not found with id: " + id));
    }

    @Override
    public PropertyValuationPurpose createPurpose(PropertyValuationPurpose purpose) {
        return purposeRepository.save(purpose);
    }

    @Override
    public PropertyValuationPurpose updatePurpose(Long id, PropertyValuationPurpose purpose) {
        return purposeRepository.findById(id)
                .map(existingPurpose -> {
                    purpose.setId(id);
                    return purposeRepository.save(purpose);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Purpose not found with id: " + id));
    }

    @Override
    public void deletePurpose(Long id) {
        purposeRepository.deleteById(id);
    }
}
