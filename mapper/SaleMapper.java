package ma.hariti.asmaa.wrm.citrontrack.mapper;

import ma.hariti.asmaa.wrm.citrontrack.dto.sale.SaleDTO;
import ma.hariti.asmaa.wrm.citrontrack.entity.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {CustomerMapper.class, HarvestMapper.class})
public interface SaleMapper {
    @Mapping(source = "customer", target = "customer")
    @Mapping(source = "harvest", target = "harvest")
    SaleDTO toDTO(Sale sale);

    @Mapping(source = "customer", target = "customer")
    @Mapping(source = "harvest", target = "harvest")
    Sale toEntity(SaleDTO saleDTO);
}