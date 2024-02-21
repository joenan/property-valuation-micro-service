package com.mcb.bpea.service.impl;

import com.mcb.bpea.entities.Facility;
import com.mcb.bpea.exception.ResourceNotFoundException;
import com.mcb.bpea.repository.FacilityRepository;
import com.mcb.bpea.service.FacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacilityServiceImpl implements FacilityService {

    private final FacilityRepository facilityRepository;

    @Override
    public List<Facility> getAllFacilities() {
        return facilityRepository.findAll();
    }

    @Override
    public Facility getFacilityById(Long id) {
        return facilityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Facility not found with id: " + id));
    }

    @Override
    public Facility createFacility(Facility facility) {
        return facilityRepository.save(facility);
    }

    @Override
    public Facility updateFacility(Long id, Facility facility) {
        return facilityRepository.findById(id)
                .map(existingFacility -> {
                    facility.setId(id);
                    return facilityRepository.save(facility);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Facility not found with id: " + id));
    }

    @Override
    public void deleteFacility(Long id) {
        facilityRepository.deleteById(id);
    }
}
