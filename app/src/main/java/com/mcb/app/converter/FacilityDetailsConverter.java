package com.mcb.app.converter;


import com.mcb.commons.dto.FacilityDetailsDto;
import com.mcb.commons.entities.FacilityDetails;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface FacilityDetailsConverter {
    FacilityDetailsDto toFacilityDetailsDto(FacilityDetails source);
    FacilityDetails toFacilityDetails(FacilityDetailsDto source);

}
