package ma.hariti.asmaa.wrm.citrontrack.dto.harvest;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.hariti.asmaa.wrm.citrontrack.enums.Season;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HarvestDTO {

    @NotNull(message = "Harvest ID cannot be null")
    private Long id;

    @NotNull(message = "Harvest date cannot be null")
    @PastOrPresent(message = "Harvest date must be in the past or present")
    private LocalDate harvestDate;

    @NotNull(message = "Season cannot be null")
    private Season season;

    @Positive(message = "Total quantity must be greater than 0")
    private double totalQuantity;
}
