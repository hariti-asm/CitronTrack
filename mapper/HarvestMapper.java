package ma.hariti.asmaa.wrm.citrontrack.mapper;
import ma.hariti.asmaa.wrm.citrontrack.dto.harvest.HarvestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.harvest.HarvestRequestDTO;
import ma.hariti.asmaa.wrm.citrontrack.entity.Harvest;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HarvestMapper {

    @Mapping(target = "harvestDetails", ignore = true)
    Harvest toEntity(HarvestDTO harvestDTO);

    @Mapping(target = "totalQuantity", expression = "java(calculateTotalQuantity(harvest))")
    HarvestDTO toDto(Harvest harvest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "harvestDetails", ignore = true)
    @Mapping(target = "totalQuantity", ignore = true)
    Harvest requestDtoToEntity(HarvestRequestDTO requestDTO);

    @AfterMapping
    default void updateTotalQuantity(@MappingTarget Harvest harvest) {
        if (harvest.getHarvestDetails() != null && !harvest.getHarvestDetails().isEmpty()) {
            double total = harvest.getHarvestDetails().stream()
                    .mapToDouble(detail -> detail.getQuantity())
                    .sum();
            harvest.setTotalQuantity(total);
        }
    }

    default double calculateTotalQuantity(Harvest harvest) {
        if (harvest.getHarvestDetails() == null || harvest.getHarvestDetails().isEmpty()) {
            return 0.0;
        }
        return harvest.getHarvestDetails().stream()
                .mapToDouble(detail -> detail.getQuantity())
                .sum();
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(HarvestDTO harvestDTO, @MappingTarget Harvest harvest);
}