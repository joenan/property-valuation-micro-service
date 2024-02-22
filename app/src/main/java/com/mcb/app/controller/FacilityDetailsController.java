package com.mcb.app.controller;


import com.mcb.app.service.FacilityDetailsService;
import com.mcb.commons.entities.FacilityDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facility-details")
@RequiredArgsConstructor
public class FacilityDetailsController {

    private final FacilityDetailsService facilityDetailsService;

    @PostMapping
    public FacilityDetails saveFacilityDetails(@RequestBody FacilityDetails facilityDetails) {
        return facilityDetailsService.saveFacilityDetails(facilityDetails);
    }

    @GetMapping("/{id}")
    public FacilityDetails getFacilityDetailsById(@PathVariable Long id) {
        return facilityDetailsService.getFacilityDetailsById(id);
    }

    @GetMapping
    public List<FacilityDetails> getAllFacilityDetails() {
        return facilityDetailsService.getAllFacilityDetails();
    }

    @DeleteMapping("/{id}")
    public void deleteFacilityDetails(@PathVariable Long id) {
        facilityDetailsService.deleteFacilityDetails(id);
    }
}