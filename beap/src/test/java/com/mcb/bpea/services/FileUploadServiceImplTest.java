package com.mcb.bpea.services;

import com.mcb.bpea.entities.*;
import com.mcb.bpea.enums.Category;
import com.mcb.bpea.enums.Currency;
import com.mcb.bpea.enums.DocumentType;
import com.mcb.bpea.enums.FacilityType;
import com.mcb.bpea.repository.FileUploadRepository;
import com.mcb.bpea.service.impl.FileUploadServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FileUploadServiceImplTest {

    @Mock
    private FileUploadRepository fileUploadRepository;

    @InjectMocks
    private FileUploadServiceImpl fileUploadService;

    @Test
    void saveFileUpload() {
        FileUpload fileUpload = createSampleFileUpload();

        when(fileUploadRepository.save(fileUpload)).thenReturn(fileUpload);

        FileUpload savedFileUpload = fileUploadService.saveFileUpload(fileUpload);

        assertNotNull(savedFileUpload);
        assertEquals(fileUpload, savedFileUpload);

        verify(fileUploadRepository, times(1)).save(fileUpload);
    }

    @Test
    void getFileUploadById() {
        Long fileUploadId = 1L;
        FileUpload fileUpload = createSampleFileUpload();

        when(fileUploadRepository.findById(fileUploadId)).thenReturn(Optional.of(fileUpload));

        FileUpload retrievedFileUpload = fileUploadService.getFileUploadById(fileUploadId);

        assertNotNull(retrievedFileUpload);
        assertEquals(fileUpload, retrievedFileUpload);

        verify(fileUploadRepository, times(1)).findById(fileUploadId);
    }

    @Test
    void getAllFileUploads() {
        FileUpload fileUpload = createSampleFileUpload();

        when(fileUploadRepository.findAll()).thenReturn(Collections.singletonList(fileUpload));

        List<FileUpload> result = fileUploadService.getAllFileUploads();

        assertEquals(1, result.size());
        assertEquals(fileUpload, result.get(0));

        verify(fileUploadRepository, times(1)).findAll();
    }

    @Test
    void deleteFileUpload() {
        Long fileUploadId = 1L;

        fileUploadService.deleteFileUpload(fileUploadId);

        verify(fileUploadRepository, times(1)).deleteById(fileUploadId);
    }

    private FileUpload createSampleFileUpload() {
        return FileUpload.builder()
                .id(1L)
                .documentType(DocumentType.BIRTH_CERTIFICATE)
                .fileSize(123232L)
                .fileName("sample-file.txt")
                .uploadedBy(createSampleUser())
                .propertyValuation(createSamplePropertyValuation())
                .uploadedDate(LocalDateTime.now())
                .build();
    }

    private User createSampleUser() {
        return User.builder()
                .id(1L)
                .username("sampleuser")
                .password("samplepassword")
                .businessUnit("Business Unit")
                .contactNumber("Contact Number")
                .email("email@gmail.com")
                .build();
    }

    private PropertyValuation createSamplePropertyValuation() {
        return PropertyValuation.builder()
                .id(1L)
                .facilityType(FacilityType.ON_REVOLVING)
                .category("Sample Category")
                .purpose(1)
                .termMonths(12)
                .currency("USD")
                .amount(100000.0)
                .fosReference("Sample FOS Reference")
                .reference("Sample Reference")
                .evaluationType("Sample Evaluation Type")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .borrowers(Collections.singletonList(createSampleCustomer()))
                .facilityDetails(createSampleFacilityDetails())
                .build();
    }

    private Customer createSampleCustomer() {
        return Customer.builder()
                .id(1L)
                .customerNumber("123")
                .shortName("Sample Customer")
                .email("customer@gmail.com")
                .nationality("NG")
                .build();
    }

    private FacilityDetails createSampleFacilityDetails() {
        return FacilityDetails.builder()
                .id(1L)
                .facilityType(FacilityType.ON_REVOLVING)
                .category(Category.APARTMENT)
                .purposeOfPropertyValuation("Sample Purpose")
                .termInMonths(24)
                .currency(Currency.EUR)
                .amount(BigDecimal.valueOf(50000))
                .build();
    }
}
