package com.mcb.commons.dto;


import com.mcb.commons.enums.Category;
import com.mcb.commons.enums.Currency;
import com.mcb.commons.enums.FacilityType;
import com.mcb.commons.enums.Purpose;
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
    private Purpose purposeOfPropertyValuation;
    private int termInMonths;
    private Currency currency;
    private BigDecimal amount;
    private PropertyValuationDto propertyValuation;
}