package ma.hariti.asmaa.wrm.citrontrack.service.tree;

import ma.hariti.asmaa.wrm.citrontrack.dto.tree.TreeDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.tree.TreeRequestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.tree.TreeResponseDTO;
import ma.hariti.asmaa.wrm.citrontrack.util.GenericDtoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TreeService extends GenericDtoService<TreeDTO, Long> {
    TreeResponseDTO createFromRequest(TreeRequestDTO requestDTO);

    Page<TreeResponseDTO> findAllWithResponse(Pageable pageable);
    Optional<TreeResponseDTO> findByIdWithResponse(Long id);
}
