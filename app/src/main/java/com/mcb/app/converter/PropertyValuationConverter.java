package com.mcb.app.converter;

import com.mcb.commons.dto.PropertyValuationDto;
import com.mcb.commons.entities.PropertyValuation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PropertyValuationConverter {

    @Mapping(target = "borrowers", source = "source.borrowers") // specify mapping for the list
    PropertyValuationDto toPropertyValuationDto(PropertyValuation source);

    @Mapping(target = "borrowers", source = "source.borrowers") // specify mapping for the list
    PropertyValuation toPropertyValuation(PropertyValuationDto source);

    List<PropertyValuationDto> toPropertyValuationDtoList(List<PropertyValuation> sourceList);

    List<PropertyValuation> toPropertyValuationList(List<PropertyValuationDto> sourceList);
}
