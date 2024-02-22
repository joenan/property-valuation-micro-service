package com.mcb.app.converter;


import com.mcb.commons.dto.CustomerDto;
import com.mcb.commons.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CustomerConverter {

    @Mapping(target = "isIndividual", source = "individual")
    CustomerDto toCustomerDto(Customer source);

    @Mapping(target = "isIndividual", source = "individual")
    Customer toCustomer(CustomerDto source);
}
