package com.mcb.bpea.dto;

import com.mcb.bpea.enums.Category;
import com.mcb.bpea.enums.Currency;
import com.mcb.bpea.enums.FacilityType;
import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class FacilityDetailsDto {

    private Long id;
    private FacilityType facilityType;
    private Category category;
    private String purposeOfPropertyValuation;
    private int termInMonths;
    private Currency currency;
    private BigDecimal amount;
    private PropertyValuationDto propertyValuation;
}