package com.mcb.bpea.services;

import com.mcb.bpea.entities.FacilityDetails;
import com.mcb.bpea.enums.Category;
import com.mcb.bpea.enums.Currency;
import com.mcb.bpea.enums.FacilityType;
import com.mcb.bpea.repository.FacilityDetailsRepository;
import com.mcb.bpea.service.impl.FacilityDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FacilityDetailsServiceImplTest {

    @Mock
    private FacilityDetailsRepository facilityDetailsRepository;

    @InjectMocks
    private FacilityDetailsServiceImpl subject;


    @Test
    void saveFacilityDetails() {
        //Given
        FacilityDetails facilityDetails = createSampleFacilityDetails();

        when(facilityDetailsRepository.save(facilityDetails)).thenReturn(facilityDetails);

        //When
        FacilityDetails savedFacilityDetails = subject.saveFacilityDetails(facilityDetails);


        //Then
        assertNotNull(savedFacilityDetails);
        assertEquals(facilityDetails, savedFacilityDetails);

        verify(facilityDetailsRepository, times(1)).save(facilityDetails);
    }

    @Test
    void getFacilityDetailsById() {

        //Given
        Long id = 1L;
        FacilityDetails facilityDetails = createSampleFacilityDetails();

        when(facilityDetailsRepository.findById(id)).thenReturn(Optional.of(facilityDetails));

        //When
        FacilityDetails retrievedFacilityDetails = subject.getFacilityDetailsById(id);


        //Then
        assertNotNull(retrievedFacilityDetails);
        assertEquals(facilityDetails, retrievedFacilityDetails);

        verify(facilityDetailsRepository, times(1)).findById(id);
    }

    @Test
    void getAllFacilityDetails() {

        //Given
        FacilityDetails facilityDetails = createSampleFacilityDetails();

        when(facilityDetailsRepository.findAll()).thenReturn(Collections.singletonList(facilityDetails));

        //When
        List<FacilityDetails> result = subject.getAllFacilityDetails();


        //Then
        assertEquals(1, result.size());
        assertEquals(facilityDetails, result.get(0));

        verify(facilityDetailsRepository, times(1)).findAll();
    }

    @Test
    void deleteFacilityDetails() {
        //Given
        Long id = 1L;

        //When
        subject.deleteFacilityDetails(id);

        //Then
        verify(facilityDetailsRepository, times(1)).deleteById(id);
    }

    private FacilityDetails createSampleFacilityDetails() {
        return FacilityDetails.builder()
                .id(1L)
                .facilityType(FacilityType.ON_REVOLVING)
                .category(Category.APARTMENT)
                .purposeOfPropertyValuation("Sample purpose")
                .termInMonths(12)
                .currency(Currency.EUR)
                .amount(BigDecimal.valueOf(100000))
                .build();
    }
}
