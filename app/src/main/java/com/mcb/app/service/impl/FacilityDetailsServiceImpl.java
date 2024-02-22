package com.mcb.app.service.impl;

import com.mcb.app.repository.FacilityDetailsRepository;
import com.mcb.app.service.FacilityDetailsService;
import com.mcb.commons.entities.FacilityDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacilityDetailsServiceImpl implements FacilityDetailsService {


    private final FacilityDetailsRepository facilityDetailsRepository;

    @Override
    public FacilityDetails saveFacilityDetails(FacilityDetails facilityDetails) {
        return facilityDetailsRepository.save(facilityDetails);
    }

    @Override
    public FacilityDetails getFacilityDetailsById(Long id) {
        return facilityDetailsRepository.findById(id).orElse(null);
    }

    @Override
    public List<FacilityDetails> getAllFacilityDetails() {
        return facilityDetailsRepository.findAll();
    }

    @Override
    public void deleteFacilityDetails(Long id) {
        facilityDetailsRepository.deleteById(id);
    }
}
