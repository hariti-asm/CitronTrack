package ma.hariti.asmaa.wrm.citrontrack.controller;

import jakarta.validation.Valid;
import ma.hariti.asmaa.wrm.citrontrack.dto.harvestDetail.HarvestDetailRequestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.harvestDetail.HarvestDetailResponseDTO;
import ma.hariti.asmaa.wrm.citrontrack.embeddedable.HarvestDetailId;
import ma.hariti.asmaa.wrm.citrontrack.service.harvestDetail.HarvestDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/harvest-details")
public class HarvestDetailController {

    private final HarvestDetailService harvestDetailService;

    public HarvestDetailController(HarvestDetailService harvestDetailService) {
        this.harvestDetailService = harvestDetailService;
    }

    @PostMapping
    public ResponseEntity<HarvestDetailResponseDTO> createHarvestDetail(
            @Valid @RequestBody HarvestDetailRequestDTO requestDTO) {
        HarvestDetailResponseDTO createdHarvestDetail = harvestDetailService.createFromRequest(requestDTO);
        return ResponseEntity.ok(createdHarvestDetail);
    }

    @PutMapping("/{harvestId}/{treeId}")
    public ResponseEntity<HarvestDetailResponseDTO> updateHarvestDetail(
            @PathVariable Long harvestId,
            @PathVariable Long treeId,
            @Valid @RequestBody HarvestDetailRequestDTO requestDTO) {
        HarvestDetailId id = new HarvestDetailId(harvestId, treeId);
        HarvestDetailResponseDTO updatedHarvestDetail = harvestDetailService.updateFromRequest(id, requestDTO);
        return ResponseEntity.ok(updatedHarvestDetail);
    }
}