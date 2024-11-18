package ma.hariti.asmaa.wrm.citrontrack.dto.harvestDetail;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ma.hariti.asmaa.wrm.citrontrack.dto.tree.TreeDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.harvest.HarvestDTO;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class HarvestDetailResponseDTO {

    @NotNull(message = "Harvest ID cannot be null")
    private Long harvestId;

    @NotNull(message = "Tree ID cannot be null")
    private Long treeId;

    @Positive(message = "Quantity must be greater than 0")
    private Double quantity;

    @NotNull(message = "Tree cannot be null")
    private TreeDTO tree;

    @NotNull(message = "Harvest cannot be null")
    private HarvestDTO harvest;
}
