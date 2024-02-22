package com.mcb.bpea.service;

import com.mcb.bpea.entities.Facility;
import com.mcb.bpea.exception.ResourceNotFoundException;
import com.mcb.bpea.repository.FacilityRepository;
import com.mcb.bpea.service.impl.FacilityServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FacilityServiceImplTest {

    @Mock
    private FacilityRepository facilityRepository;

    @InjectMocks
    private FacilityServiceImpl subject;

    @Test
    void givenExistingFacilities_whenGetAllFacilities_thenReturnsListOfFacilities() {
        // Given
        List<Facility> facilities = Arrays.asList(
                new Facility(1L, "Facility1"),
                new Facility(2L, "Facility2")
        );
        when(facilityRepository.findAll()).thenReturn(facilities);

        // When
        List<Facility> result = subject.getAllFacilities();

        // Then
        assertEquals(facilities, result);
        verify(facilityRepository, times(1)).findAll();
    }

    @Test
    void givenExistingFacilityId_whenGetFacilityById_thenReturnsFacility() {
        // Given
        Long facilityId = 1L;
        Facility facility = new Facility(facilityId, "Facility1");
        when(facilityRepository.findById(facilityId)).thenReturn(Optional.of(facility));

        // When
        Facility result = subject.getFacilityById(facilityId);

        // Then
        assertEquals(facility, result);
        verify(facilityRepository, times(1)).findById(facilityId);
    }

    @Test
    void givenNonExistingFacilityId_whenGetFacilityById_thenThrowsResourceNotFoundException() {
        // Given
        Long facilityId = 1L;
        when(facilityRepository.findById(facilityId)).thenReturn(Optional.empty());

        // When and Then
        assertThrows(ResourceNotFoundException.class, () -> subject.getFacilityById(facilityId));
        verify(facilityRepository, times(1)).findById(facilityId);
    }

    @Test
    void givenFacilityToCreate_whenCreateFacility_thenReturnsCreatedFacility() {
        // Given
        Facility facilityToCreate = new Facility(null, "NewFacility");
        Facility createdFacility = new Facility(1L, "NewFacility");
        when(facilityRepository.save(facilityToCreate)).thenReturn(createdFacility);

        // When
        Facility result = subject.createFacility(facilityToCreate);

        // Then
        assertEquals(createdFacility, result);
        verify(facilityRepository, times(1)).save(facilityToCreate);
    }

    @Test
    void givenExistingFacilityIdAndUpdatedFacility_whenUpdateFacility_thenReturnsUpdatedFacility() {
        // Given
        Long facilityId = 1L;
        Facility existingFacility = new Facility(facilityId, "ExistingFacility");
        Facility updatedFacility = new Facility(facilityId, "UpdatedFacility");

        when(facilityRepository.findById(facilityId)).thenReturn(Optional.of(existingFacility));
        when(facilityRepository.save(updatedFacility)).thenReturn(updatedFacility);

        // When
        Facility result = subject.updateFacility(facilityId, updatedFacility);

        // Then
        assertEquals(updatedFacility, result);
        verify(facilityRepository, times(1)).findById(facilityId);
        verify(facilityRepository, times(1)).save(updatedFacility);
    }

    @Test
    void givenNonExistingFacilityIdAndUpdatedFacility_whenUpdateFacility_thenThrowsResourceNotFoundException() {
        // Given
        Long facilityId = 1L;
        Facility updatedFacility = new Facility(facilityId, "UpdatedFacility");

        when(facilityRepository.findById(facilityId)).thenReturn(Optional.empty());

        // When and Then
        assertThrows(ResourceNotFoundException.class, () -> subject.updateFacility(facilityId, updatedFacility));
        verify(facilityRepository, times(1)).findById(facilityId);
        verify(facilityRepository, never()).save(updatedFacility);
    }

    @Test
    void givenExistingFacilityId_whenDeleteFacility_thenDeletesFacility() {
        // Given
        Long facilityId = 1L;

        // When
        subject.deleteFacility(facilityId);

        // Then
        verify(facilityRepository, times(1)).deleteById(facilityId);
    }
}
