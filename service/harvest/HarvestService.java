package ma.hariti.asmaa.wrm.citrontrack.service.harvest;

import ma.hariti.asmaa.wrm.citrontrack.dto.harvest.HarvestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.harvest.HarvestRequestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.harvest.HarvestResponseDTO;
import ma.hariti.asmaa.wrm.citrontrack.util.GenericDtoService;

public interface HarvestService extends GenericDtoService<HarvestDTO, Long> {
    HarvestResponseDTO createHarvest(HarvestRequestDTO requestDTO);

}
