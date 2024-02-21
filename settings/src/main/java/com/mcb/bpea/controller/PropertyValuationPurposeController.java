package com.mcb.bpea.controller;

import com.mcb.bpea.entities.PropertyValuationPurpose;
import com.mcb.bpea.service.PropertyValuationPurposeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purposes")
@RequiredArgsConstructor
public class PropertyValuationPurposeController {

    private final PropertyValuationPurposeService purposeService;

    @GetMapping
    public List<PropertyValuationPurpose> getAllPurposes() {
        return purposeService.getAllPurposes();
    }

    @GetMapping("/{id}")
    public PropertyValuationPurpose getPurposeById(@PathVariable Long id) {
        return purposeService.getPurposeById(id);
    }

    @PostMapping
    public PropertyValuationPurpose createPurpose(@RequestBody PropertyValuationPurpose purpose) {
        return purposeService.createPurpose(purpose);
    }

    @PutMapping("/{id}")
    public PropertyValuationPurpose updatePurpose(@PathVariable Long id, @RequestBody PropertyValuationPurpose purpose) {
        return purposeService.updatePurpose(id, purpose);
    }

    @DeleteMapping("/{id}")
    public void deletePurpose(@PathVariable Long id) {
        purposeService.deletePurpose(id);
    }
}
