package ma.hariti.asmaa.wrm.citrontrack.dto.harvest;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import ma.hariti.asmaa.wrm.citrontrack.enums.Season;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HarvestRequestDTO {
    @NotNull(message = "Harvest date is required")
    @PastOrPresent(message = "Harvest date must be in the past or present")
    private LocalDate harvestDate;

    @NotNull(message = "Season is required")
    private Season season;
}
