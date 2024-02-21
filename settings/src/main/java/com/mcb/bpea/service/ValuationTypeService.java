package com.mcb.bpea.service;

import com.mcb.bpea.entities.ValuationType;

import java.util.List;

public interface ValuationTypeService {
    List<ValuationType> getAllTypes();
    ValuationType getTypeById(Long id);
    ValuationType createType(ValuationType type);
    ValuationType updateType(Long id, ValuationType type);
    void deleteType(Long id);
}
