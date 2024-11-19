package ma.hariti.asmaa.wrm.citrontrack.mapper;

import ma.hariti.asmaa.wrm.citrontrack.dto.field.FieldDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.field.FieldRequestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.field.FieldResponseDTO;
import ma.hariti.asmaa.wrm.citrontrack.entity.Field;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FieldMapper {

    FieldDTO toDto(Field entity);

    Field toEntity(FieldDTO dto);

    FieldResponseDTO toResponseDto(Field entity);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(FieldDTO dto, @MappingTarget Field entity);

    List<FieldDTO> toDtoList(List<Field> fields);

    List<FieldResponseDTO> toResponseDtoList(List<Field> fields);

    FieldDTO toDtoFromRequest(FieldRequestDTO requestDTO);
}
