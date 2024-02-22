package com.mcb.app.controller;

import com.mcb.app.service.ValuationTypeService;
import com.mcb.commons.entities.ValuationType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/types")
@RequiredArgsConstructor
public class ValuationTypeController {


    private final ValuationTypeService typeService;

    @GetMapping
    public List<ValuationType> getAllTypes() {
        return typeService.getAllTypes();
    }

    @GetMapping("/{id}")
    public ValuationType getTypeById(@PathVariable Long id) {
        return typeService.getTypeById(id);
    }

    @PostMapping
    public ValuationType createType(@RequestBody ValuationType type) {
        return typeService.createType(type);
    }

    @PutMapping("/{id}")
    public ValuationType updateType(@PathVariable Long id, @RequestBody ValuationType type) {
        return typeService.updateType(id, type);
    }

    @DeleteMapping("/{id}")
    public void deleteType(@PathVariable Long id) {
        typeService.deleteType(id);
    }
}
