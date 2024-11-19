package ma.hariti.asmaa.wrm.citrontrack.service.field;

import ma.hariti.asmaa.wrm.citrontrack.dto.field.FieldDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.field.FieldRequestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.field.FieldResponseDTO;
import ma.hariti.asmaa.wrm.citrontrack.util.GenericDtoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface FieldService extends GenericDtoService<FieldDTO, Long> {
    Optional<FieldResponseDTO> findByIdWithResponse(Long id);
    FieldResponseDTO createFromRequest(FieldRequestDTO requestDTO);

    Page<FieldResponseDTO> findAllWithResponse(Pageable pageable);

}
