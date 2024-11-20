package ma.hariti.asmaa.wrm.citrontrack.service.tree;

import ma.hariti.asmaa.wrm.citrontrack.dto.harvest.HarvestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.tree.TreeDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.tree.TreeRequestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.tree.TreeResponseDTO;
import ma.hariti.asmaa.wrm.citrontrack.util.GenericDtoService;

import java.util.Optional;

public interface TreeService extends GenericDtoService<TreeDTO, Long> {
    TreeResponseDTO createFromRequest(TreeRequestDTO requestDTO);

    Optional<TreeResponseDTO> findByIdWithResponse(Long id);
}
