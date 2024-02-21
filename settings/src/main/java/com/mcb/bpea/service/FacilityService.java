package com.mcb.bpea.service;

import com.mcb.bpea.entities.Facility;

import java.util.List;

public interface FacilityService {
    List<Facility> getAllFacilities();
    Facility getFacilityById(Long id);
    Facility createFacility(Facility facility);
    Facility updateFacility(Long id, Facility facility);
    void deleteFacility(Long id);
}
