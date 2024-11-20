package ma.hariti.asmaa.wrm.citrontrack.mapper;

import ma.hariti.asmaa.wrm.citrontrack.dto.harvestDetail.HarvestDetailRequestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.harvestDetail.HarvestDetailResponseDTO;
import ma.hariti.asmaa.wrm.citrontrack.entity.HarvestDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {TreeMapper.class, HarvestMapper.class})
public interface HarvestDetailMapper {

    @Mapping(source = "treeId", target = "id.treeId")
    @Mapping(source = "harvestId", target = "id.harvestId")
    HarvestDetail toEntityFromRequest(HarvestDetailRequestDTO requestDTO);

    @Mapping(source = "id.treeId", target = "treeId")
    @Mapping(source = "id.harvestId", target = "harvestId")
    HarvestDetailResponseDTO toResponseDto(HarvestDetail entity);

    @Mapping(source = "treeId", target = "id.treeId")
    @Mapping(source = "harvestId", target = "id.harvestId")
    void updateEntityFromDto(HarvestDetailRequestDTO dto, @MappingTarget HarvestDetail entity);
}