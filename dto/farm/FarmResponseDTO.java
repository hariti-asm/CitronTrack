package ma.hariti.asmaa.wrm.citrontrack.dto.farm;


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

    private String name;

    private String location;

    private Double totalArea;

    private LocalDate creationDate;

    private List<FieldDTO> fields = new ArrayList<>();
}

