package com.mcb.bpea.service.impl;

import com.mcb.bpea.entities.PropertyValuationPurpose;
import com.mcb.bpea.exception.ResourceNotFoundException;
import com.mcb.bpea.repository.PropertyValuationPurposeRepository;
import com.mcb.bpea.service.PropertyValuationPurposeService;
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
