package ma.hariti.asmaa.wrm.citrontrack.dto.field;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldDTO {
    @NotNull(message = "Field ID cannot be null")
    private Long id;
    @NotNull(message = "Area is required")
    @DecimalMin(value = "0.1", message = "Minimum area is 0.1 hectare")
    private Double area;

}
