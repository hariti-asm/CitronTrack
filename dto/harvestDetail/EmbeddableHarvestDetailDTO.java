package ma.hariti.asmaa.wrm.citrontrack.dto.harvestDetail;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import ma.hariti.asmaa.wrm.citrontrack.dto.harvest.HarvestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.tree.TreeDTO;

@Data
public class EmbeddableHarvestDetailDTO {

    @NotNull(message = "Harvest ID cannot be null")
    private Long harvestId;

    @NotNull(message = "Tree ID cannot be null")
    private Long treeId;

    @NotNull(message = "Quantity cannot be null")
    @Positive(message = "Quantity must be greater than 0")
    private Double quantity;

    @NotNull(message = "Tree cannot be null")
    private TreeDTO tree;

    @NotNull(message = "Harvest cannot be null")
    private HarvestDTO harvest;

}
