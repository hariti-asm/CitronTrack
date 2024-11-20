package ma.hariti.asmaa.wrm.citrontrack.dto.tree;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.hariti.asmaa.wrm.citrontrack.dto.field.FieldDTO;
import ma.hariti.asmaa.wrm.citrontrack.enums.TreeProductivity;
import ma.hariti.asmaa.wrm.citrontrack.validation.ValidFieldArea;
import ma.hariti.asmaa.wrm.citrontrack.validation.ValidPlantingDate;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreeResponseDTO {

    private Long id;
    @ValidPlantingDate

    private LocalDate plantingDate;
    private FieldDTO field;
    private TreeProductivity productivity;
}