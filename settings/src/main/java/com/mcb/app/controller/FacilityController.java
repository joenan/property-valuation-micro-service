package com.mcb.app.controller;


import com.mcb.app.service.FacilityService;
import com.mcb.commons.entities.Facility;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facilities")
@RequiredArgsConstructor
public class FacilityController {

    private final FacilityService facilityService;

    @GetMapping
    public List<Facility> getAllFacilities() {
        return facilityService.getAllFacilities();
    }

    @GetMapping("/{id}")
    public Facility getFacilityById(@PathVariable Long id) {
        return facilityService.getFacilityById(id);
    }

    @PostMapping
    public Facility createFacility(@RequestBody Facility facility) {
        return facilityService.createFacility(facility);
    }

    @PutMapping("/{id}")
    public Facility updateFacility(@PathVariable Long id, @RequestBody Facility facility) {
        return facilityService.updateFacility(id, facility);
    }

    @DeleteMapping("/{id}")
    public void deleteFacility(@PathVariable Long id) {
        facilityService.deleteFacility(id);
    }
}
