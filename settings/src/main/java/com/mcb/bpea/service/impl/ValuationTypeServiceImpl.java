package com.mcb.bpea.service.impl;

import com.mcb.bpea.entities.ValuationType;
import com.mcb.bpea.repository.ValuationTypeRepository;
import com.mcb.bpea.service.ValuationTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ValuationTypeServiceImpl implements ValuationTypeService {

    private final ValuationTypeRepository typeRepository;

    @Override
    public List<ValuationType> getAllTypes() {
        return typeRepository.findAll();
    }

    @Override
    public ValuationType getTypeById(Long id) {
        return typeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ValuationType not found with id: " + id));
    }

    @Override
    public ValuationType createType(ValuationType type) {
        return typeRepository.save(type);
    }

    @Override
    public ValuationType updateType(Long id, ValuationType type) {
        return typeRepository.findById(id)
                .map(existingType -> {
                    type.setId(id);
                    return typeRepository.save(type);
                })
                .orElseThrow(() -> new NoSuchElementException("ValuationType not found with id: " + id));
    }

    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }
}
