package ma.hariti.asmaa.wrm.citrontrack.dto.farm;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import ma.hariti.asmaa.wrm.citrontrack.dto.field.FieldDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FarmResponseDTO {

    private Long id;

    @NotBlank(message = "Farm name is required.")
    private String name;

    @NotBlank(message = "Farm location is required.")
    private String location;

    @NotNull(message = "Total area is required.")
    @Positive(message = "Total area must be positive.")
    private Double totalArea;

    @NotNull(message = "Creation date is required.")
    @PastOrPresent(message = "Creation date cannot be in the future.")
    private LocalDate creationDate;

    @Size(max = 10, message = "A farm cannot have more than 10 fields.")
    @Valid
    private List<FieldDTO> fields = new ArrayList<>();
}
