package ma.hariti.asmaa.wrm.citrontrack.dto.tree;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreeRequestDTO {

    @NotNull(message = "Planting date is required")
    @PastOrPresent(message = "Planting date must be in the past or present")
    private LocalDate plantingDate;

    @NotNull(message = "Field ID is required")
    private Long fieldId;
}