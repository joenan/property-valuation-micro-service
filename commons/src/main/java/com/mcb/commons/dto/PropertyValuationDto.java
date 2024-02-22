package com.mcb.commons.dto;


import com.mcb.commons.enums.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PropertyValuationDto implements Serializable {

    private Long id;
    private FacilityType facilityType;
    private Category category;
    private Purpose purpose;
    private int termMonths;
    private Currency currency;
    private BigDecimal amount;
    private String fosReference;
    private String reference;
    private EvaluationType evaluationType;
    private List<CustomerDto> borrowers;
    private FacilityDetailsDto facilityDetails;
}