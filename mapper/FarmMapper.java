package ma.hariti.asmaa.wrm.citrontrack.mapper;
import ma.hariti.asmaa.wrm.citrontrack.dto.farm.FarmRequestDTO;
import ma.hariti.asmaa.wrm.citrontrack.entity.Farm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FarmMapper {
    FarmMapper INSTANCE = Mappers.getMapper(FarmMapper.class);

    @Mapping(target = "id", ignore = true)
    Farm toEntity(FarmRequestDTO farmRequestDTO);

    FarmRequestDTO toDto(Farm farm);
}