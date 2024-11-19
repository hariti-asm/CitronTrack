package ma.hariti.asmaa.wrm.citrontrack.mapper;

import ma.hariti.asmaa.wrm.citrontrack.dto.field.FieldDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.field.FieldRequestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.field.FieldResponseDTO;
import ma.hariti.asmaa.wrm.citrontrack.entity.Field;
import ma.hariti.asmaa.wrm.citrontrack.entity.Farm;
import ma.hariti.asmaa.wrm.citrontrack.repository.FarmRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class FieldMapper {

    @Autowired
    private FarmRepository farmRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "farm", expression = "java(getFarmById(dto.getFarmId()))")
    @Mapping(target = "trees", ignore = true)
    public abstract Field toEntity(FieldRequestDTO dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "area", source = "area")
    @Mapping(target = "farm", source = "farm")
    @Mapping(target = "trees", source = "trees")
    public abstract FieldResponseDTO toResponseDto(Field entity);

    @Mapping(target = "area", source = "area")
    public abstract FieldDTO toDto(Field entity);

    @Mapping(target = "id", ignore = true)
    public abstract void updateEntityFromDto(FieldDTO dto, @MappingTarget Field entity);

    public abstract List<FieldDTO> toDtoList(List<Field> fields);

    public abstract List<FieldResponseDTO> toResponseDtoList(List<Field> fields);

    protected Farm getFarmById(Long farmId) {
        return farmRepository.findById(farmId)
                .orElseThrow(() -> new IllegalArgumentException("Farm not found with ID: " + farmId));
    }
}
