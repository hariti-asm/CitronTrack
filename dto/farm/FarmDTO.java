package ma.hariti.asmaa.wrm.citrontrack.dto.farm;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FarmDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Total area is required")
    @DecimalMin(value = "0.1", message = "Minimum area is 0.1 hectare")
    private Double totalArea;

    @PastOrPresent(message = "Creation date must be in the past or present")
    private LocalDate creationDate;
}

