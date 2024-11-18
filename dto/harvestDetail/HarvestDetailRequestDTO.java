package ma.hariti.asmaa.wrm.citrontrack.dto.harvestDetail;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HarvestDetailRequestDTO {

    @NotNull(message = "Quantity cannot be null")
    @Positive(message = "Quantity must be greater than 0")
    private Double quantity;

    @NotNull(message = "Harvest ID cannot be null")
    private Long harvestId;

    @NotNull(message = "Tree ID cannot be null")
    private Long treeId;
}
