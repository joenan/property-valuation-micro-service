package com.mcb.commons.dto;


import com.mcb.commons.enums.FacilityType;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PropertyValuationDto implements Serializable {

    private Long id;
    private FacilityType facilityType;
    private String category;
    private int purpose;
    private int termMonths;
    private String currency;
    private double amount;
    private String fosReference;
    private String reference;
    private String evaluationType;
    private List<CustomerDto> borrowers;
    private FacilityDetailsDto facilityDetails;
}