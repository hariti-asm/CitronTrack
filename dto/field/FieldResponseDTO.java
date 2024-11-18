package ma.hariti.asmaa.wrm.citrontrack.dto.field;
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
    private Double area;
    private FarmDTO farm;
    private List<TreeDTO> trees;
}
