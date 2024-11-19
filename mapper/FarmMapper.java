package ma.hariti.asmaa.wrm.citrontrack.mapper;

import ma.hariti.asmaa.wrm.citrontrack.dto.farm.FarmDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.field.FieldDTO;
import ma.hariti.asmaa.wrm.citrontrack.entity.Farm;
import ma.hariti.asmaa.wrm.citrontrack.entity.Field;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FarmMapper {
    @Mapping(target = "id", ignore = true)
    Farm toEntity(FarmDTO farmDTO);

    FarmDTO toDto(Farm farm);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(FarmDTO farmDTO, @MappingTarget Farm farm);
    List<FieldDTO> mapFields(List<Field> fields);

}