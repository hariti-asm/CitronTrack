package ma.hariti.asmaa.wrm.citrontrack.service.farm;

import ma.hariti.asmaa.wrm.citrontrack.builder.FarmSpecificationBuilder;
import ma.hariti.asmaa.wrm.citrontrack.dto.farm.FarmDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.farm.FarmRequestDTO;
import ma.hariti.asmaa.wrm.citrontrack.util.GenericDtoService;

import java.util.List;

public interface FarmService extends GenericDtoService<FarmDTO, Long> {
    FarmDTO createFromRequest(FarmRequestDTO requestDTO);
    List<FarmDTO> searchFarms(FarmSpecificationBuilder builder);

}
