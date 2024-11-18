package ma.hariti.asmaa.wrm.citrontrack.dto.field;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import ma.hariti.asmaa.wrm.citrontrack.dto.farm.FarmDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.tree.TreeDTO;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldResponseDTO {

    private Long id;
    @NotNull(message = "Field area is required.")
    @Min(value = 1000, message = "The area must be at least 0.1 hectare (1000 m²).")
    private Double area;
    @NotNull(message = "Farm information is required.")
    @Valid
    private FarmDTO farm;
    @NotNull(message = "List of trees is required.")
    @Valid
    private List<@Size(max = 10, message = "The field cannot contain more than 10 trees per 1000 m².") TreeDTO> trees;
}
