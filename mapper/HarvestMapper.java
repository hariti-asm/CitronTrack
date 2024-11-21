package ma.hariti.asmaa.wrm.citrontrack.mapper;

import ma.hariti.asmaa.wrm.citrontrack.dto.harvest.HarvestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.harvest.HarvestRequestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.harvest.HarvestResponseDTO;
import ma.hariti.asmaa.wrm.citrontrack.entity.Harvest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface HarvestMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "harvestDate", target = "harvestDate")
    @Mapping(source = "season", target = "season")
    @Mapping(source = "totalQuantity", target = "totalQuantity")
    HarvestDTO toDTO(Harvest harvest);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "harvestDate", target = "harvestDate")
    @Mapping(source = "season", target = "season")
    @Mapping(source = "totalQuantity", target = "totalQuantity")
    Harvest toEntity(HarvestDTO harvestDTO);

    // Update entity from DTO method
    void updateEntityFromDto(HarvestDTO dto, @MappingTarget Harvest entity);

    // Convert request DTO to entity
    Harvest requestDtoToEntity(HarvestRequestDTO requestDTO);

    // Convert entity to response DTO
    HarvestResponseDTO entityToResponseDto(Harvest harvest);
}