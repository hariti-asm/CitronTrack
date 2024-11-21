package ma.hariti.asmaa.wrm.citrontrack.dto.harvest;

import lombok.Data;
import ma.hariti.asmaa.wrm.citrontrack.dto.harvestDetail.EmbeddableHarvestDetailDTO;
import ma.hariti.asmaa.wrm.citrontrack.enums.Season;

import java.time.LocalDate;
import java.util.List;

@Data
public class HarvestResponseDTO {
    private Long id;
    private LocalDate harvestDate;
    private Season season;
    private double totalQuantity;
    private List<EmbeddableHarvestDetailDTO> harvestDetails;
}
