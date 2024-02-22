package com.mcb.app.service;



import com.mcb.commons.entities.FacilityDetails;

import java.util.List;

public interface FacilityDetailsService {
    FacilityDetails saveFacilityDetails(FacilityDetails facilityDetails);
    FacilityDetails getFacilityDetailsById(Long id);
    List<FacilityDetails> getAllFacilityDetails();
    void deleteFacilityDetails(Long id);
}