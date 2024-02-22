package com.mcb.app.service;

import com.mcb.app.repository.PropertyValuationPurposeRepository;
import com.mcb.app.service.impl.PropertyValuationPurposeServiceImpl;
import com.mcb.commons.entities.PropertyValuationPurpose;
import com.mcb.commons.exception.ResourceNotFoundException;
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
class PropertyValuationPurposeServiceImplTest {

    @Mock
    private PropertyValuationPurposeRepository propertyValuationPurposeRepository;

    @InjectMocks
    private PropertyValuationPurposeServiceImpl subject;

    @Test
    void givenExistingPropertyValuationPurposes_whenGetAllPropertyValuationPurposes_thenReturnsListOfPurposes() {
        // Given
        List<PropertyValuationPurpose> purposes = Arrays.asList(
                new PropertyValuationPurpose(1L, "Purpose1"),
                new PropertyValuationPurpose(2L, "Purpose2")
        );
        when(propertyValuationPurposeRepository.findAll()).thenReturn(purposes);

        // When
        List<PropertyValuationPurpose> result = subject.getAllPurposes();

        // Then
        assertEquals(purposes, result);
        verify(propertyValuationPurposeRepository, times(1)).findAll();
    }

    @Test
    void givenExistingPurposeId_whenGetPropertyValuationPurposeById_thenReturnsPurpose() {
        // Given
        Long purposeId = 1L;
        PropertyValuationPurpose purpose = new PropertyValuationPurpose(purposeId, "Purpose1");
        when(propertyValuationPurposeRepository.findById(purposeId)).thenReturn(Optional.of(purpose));

        // When
        PropertyValuationPurpose result = subject.getPurposeById(purposeId);

        // Then
        assertEquals(purpose, result);
        verify(propertyValuationPurposeRepository, times(1)).findById(purposeId);
    }

    @Test
    void givenNonExistingPurposeId_whenGetPropertyValuationPurposeById_thenThrowsResourceNotFoundException() {
        // Given
        Long purposeId = 1L;
        when(propertyValuationPurposeRepository.findById(purposeId)).thenReturn(Optional.empty());

        // When and Then
        assertThrows(ResourceNotFoundException.class, () -> subject.getPurposeById(purposeId));
        verify(propertyValuationPurposeRepository, times(1)).findById(purposeId);
    }

    @Test
    void givenPurposeToCreate_whenCreatePropertyValuationPurpose_thenReturnsCreatedPurpose() {
        // Given
        PropertyValuationPurpose purposeToCreate = new PropertyValuationPurpose(null, "NewPurpose");
        PropertyValuationPurpose createdPurpose = new PropertyValuationPurpose(1L, "NewPurpose");
        when(propertyValuationPurposeRepository.save(purposeToCreate)).thenReturn(createdPurpose);

        // When
        PropertyValuationPurpose result = subject.createPurpose(purposeToCreate);

        // Then
        assertEquals(createdPurpose, result);
        verify(propertyValuationPurposeRepository, times(1)).save(purposeToCreate);
    }

    @Test
    void givenExistingPurposeIdAndUpdatedPurpose_whenUpdatePropertyValuationPurpose_thenReturnsUpdatedPurpose() {
        // Given
        Long purposeId = 1L;
        PropertyValuationPurpose existingPurpose = new PropertyValuationPurpose(purposeId, "ExistingPurpose");
        PropertyValuationPurpose updatedPurpose = new PropertyValuationPurpose(purposeId, "UpdatedPurpose");

        when(propertyValuationPurposeRepository.findById(purposeId)).thenReturn(Optional.of(existingPurpose));
        when(propertyValuationPurposeRepository.save(updatedPurpose)).thenReturn(updatedPurpose);

        // When
        PropertyValuationPurpose result = subject.updatePurpose(purposeId, updatedPurpose);

        // Then
        assertEquals(updatedPurpose, result);
        verify(propertyValuationPurposeRepository, times(1)).findById(purposeId);
        verify(propertyValuationPurposeRepository, times(1)).save(updatedPurpose);
    }

    @Test
    void givenNonExistingPurposeIdAndUpdatedPurpose_whenUpdatePropertyValuationPurpose_thenThrowsResourceNotFoundException() {
        // Given
        Long purposeId = 1L;
        PropertyValuationPurpose updatedPurpose = new PropertyValuationPurpose(purposeId, "UpdatedPurpose");

        when(propertyValuationPurposeRepository.findById(purposeId)).thenReturn(Optional.empty());

        // When and Then
        assertThrows(ResourceNotFoundException.class, () -> subject.updatePurpose(purposeId, updatedPurpose));
        verify(propertyValuationPurposeRepository, times(1)).findById(purposeId);
        verify(propertyValuationPurposeRepository, never()).save(updatedPurpose);
    }

    @Test
    void givenExistingPurposeId_whenDeletePropertyValuationPurpose_thenDeletesPurpose() {
        // Given
        Long purposeId = 1L;

        // When
        subject.deletePurpose(purposeId);

        // Then
        verify(propertyValuationPurposeRepository, times(1)).deleteById(purposeId);
    }
}
