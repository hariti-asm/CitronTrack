package ma.hariti.asmaa.wrm.citrontrack.dto.field;

import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.DecimalMin;
import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldDTO {
    @NotNull(message = "Area is required")
    @DecimalMin(value = "0.1", message = "Minimum area is 0.1 hectare")
    private Double area;

    @NotNull(message = "Farm ID is required")
    private Long farmId;
}
