package ma.hariti.asmaa.wrm.citrontrack.service.harvestDetail;


import ma.hariti.asmaa.wrm.citrontrack.dto.harvestDetail.HarvestDetailRequestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.harvestDetail.HarvestDetailResponseDTO;
import ma.hariti.asmaa.wrm.citrontrack.embeddedable.HarvestDetailId;
import ma.hariti.asmaa.wrm.citrontrack.util.GenericDtoService;

public interface HarvestDetailService extends GenericDtoService<HarvestDetailRequestDTO, HarvestDetailId> {
    HarvestDetailResponseDTO createFromRequest(HarvestDetailRequestDTO requestDTO);
    HarvestDetailResponseDTO updateFromRequest(HarvestDetailId id, HarvestDetailRequestDTO requestDTO);
}