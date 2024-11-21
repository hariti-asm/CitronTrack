package ma.hariti.asmaa.wrm.citrontrack.mapper;

import ma.hariti.asmaa.wrm.citrontrack.dto.customer.CustomerDTO;
import ma.hariti.asmaa.wrm.citrontrack.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {
    CustomerDTO toDTO(Customer customer);

    Customer toEntity(CustomerDTO customerDTO);
}