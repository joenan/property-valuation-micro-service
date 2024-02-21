package com.mcb.bpea.service.impl;

import com.mcb.bpea.converter.CustomerConverter;
import com.mcb.bpea.converter.FacilityDetailsConverter;
import com.mcb.bpea.converter.PropertyValuationConverter;
import com.mcb.bpea.dto.PropertyValuationDto;
import com.mcb.bpea.dto.ValuationProjection;
import com.mcb.bpea.entities.Customer;
import com.mcb.bpea.entities.FacilityDetails;
import com.mcb.bpea.entities.PropertyValuation;
import com.mcb.bpea.entities.PropertyValuationBorrowers;
import com.mcb.bpea.exception.ResourceNotFoundException;
import com.mcb.bpea.repository.FacilityDetailsRepository;
import com.mcb.bpea.repository.PropertyValuationRepository;
import com.mcb.bpea.service.PropertyValuationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyValuationServiceImpl implements PropertyValuationService {

    private final PropertyValuationRepository propertyValuationRepository;

    private final FacilityDetailsRepository facilityDetailsRepository;

    private final PropertyValuationConverter valuationConverter;

    private final CustomerConverter customerConverter;

    private final FacilityDetailsConverter facilityDetailsConverter;

    private final EntityManager entityManager;

    @Override
    public PropertyValuationDto savePropertyValuation(PropertyValuationDto propertyValuationDto) {


        List<Customer> borrowers = propertyValuationDto.getBorrowers().stream()
                .map(customerConverter::toCustomer)
                .collect(Collectors.toList());

        FacilityDetails facilityDetails = facilityDetailsConverter.toFacilityDetails(propertyValuationDto.getFacilityDetails());
        facilityDetailsRepository.save(facilityDetails);


        PropertyValuation propertyValuation = valuationConverter.toPropertyValuation(propertyValuationDto);
        propertyValuation.setBorrowers(borrowers);
        propertyValuation.setFosReference(generatePofReference());
        propertyValuation.setReference(generateReference());
        propertyValuation.setFacilityDetails(facilityDetails);

        return valuationConverter.toPropertyValuationDto(propertyValuationRepository.save(propertyValuation));
    }

    @Override
    public PropertyValuationDto getPropertyValuationByType(String facilityType) {
        PropertyValuation propertyValuation = propertyValuationRepository.findByEvaluationType(facilityType)
                .orElseThrow(() -> new ResourceNotFoundException("Property valuation with type " + facilityType + " not found"));
        return valuationConverter.toPropertyValuationDto(propertyValuation);
    }

    @Override
    public PropertyValuationDto getPropertyValuationById(Long id) {
        PropertyValuation propertyValuation = propertyValuationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property valuation with id " + id + " not found"));
        return valuationConverter.toPropertyValuationDto(propertyValuation);
    }

    @Override
    public List<PropertyValuationDto> getAllPropertyValuations(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PropertyValuation> customerPage = propertyValuationRepository.findAll(pageable);
        return customerPage.getContent()
                .stream()
                .map(valuationConverter::toPropertyValuationDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePropertyValuationById(Long id) {
        if (!propertyValuationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Comment with id " + id + " not found");
        }
        propertyValuationRepository.deleteById(id);
    }

    private String generatePofReference() {

        //creating POF Reference
        LocalDateTime currentTime = LocalDateTime.now(ZoneOffset.UTC);

        //Format year as 'yyyy'
        String formattedYear = currentTime.format(DateTimeFormatter.ofPattern("yyyy"));

        // Format month as 'MM'
        String formattedMonth = currentTime.format(DateTimeFormatter.ofPattern("MM"));

        String applicationSequenceNumber = generateApplicationSequenceNumber();

        return String.format("%s/%s/%s", formattedYear, formattedMonth, applicationSequenceNumber);
    }

    private String generateReference() {

        //creating POF Reference
        LocalDateTime currentTime = LocalDateTime.now(ZoneOffset.UTC);

        //Format year as 'yyyy'
        String formattedYear = currentTime.format(DateTimeFormatter.ofPattern("yyyy"));

        // Format month as 'MM'
        String formattedMonth = currentTime.format(DateTimeFormatter.ofPattern("MM"));

        String applicationSequenceNumber = generateApplicationSequenceNumber();

        return String.format("%s%s%s%s", "PV", formattedYear, formattedMonth, applicationSequenceNumber);
    }

    private String generateApplicationSequenceNumber() {
        Optional<PropertyValuation> propertyValuationOptional = propertyValuationRepository.findFirstByOrderByIdDesc();

        if (propertyValuationOptional.isPresent()) {
            PropertyValuation propertyValuation = propertyValuationOptional.get();
            String fosReference = propertyValuation.getFosReference();
            int lastSlashIndex = fosReference.lastIndexOf('/');

            // Extract the last segment after the last '/'
            String lastApplicationSequence = fosReference.substring(lastSlashIndex + 1);

            // Parse the substring to an integer, increment, and return as a string
            int incrementedApplicationSequenceNumber = Integer.parseInt(lastApplicationSequence) + 1;
            return String.format("%04d", incrementedApplicationSequenceNumber);

        } else {
            return "0001";
        }
    }

}
