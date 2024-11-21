package ma.hariti.asmaa.wrm.citrontrack.service.sale;

import ma.hariti.asmaa.wrm.citrontrack.dto.sale.SaleDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.sale.SaleRequestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.sale.SaleResponseDTO;
import ma.hariti.asmaa.wrm.citrontrack.util.GenericDtoService;

public interface SaleService extends GenericDtoService<SaleDTO, Long> {
    SaleResponseDTO createFromRequest(SaleRequestDTO requestDTO);

}
