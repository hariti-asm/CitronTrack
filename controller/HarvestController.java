package ma.hariti.asmaa.wrm.citrontrack.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.hariti.asmaa.wrm.citrontrack.dto.harvest.HarvestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.harvest.HarvestRequestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.harvest.HarvestResponseDTO;
import ma.hariti.asmaa.wrm.citrontrack.mapper.HarvestMapper;
import ma.hariti.asmaa.wrm.citrontrack.service.harvest.HarvestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/harvests")
@RequiredArgsConstructor
@Tag(name = "Harvest", description = "Harvest management endpoints")
@CrossOrigin(origins = "*")
public class HarvestController {

    private final HarvestService harvestService;
    private final HarvestMapper harvestMapper;

    @PostMapping
    public HarvestResponseDTO createHarvest(@RequestBody HarvestRequestDTO requestDTO) {
        return harvestService.createHarvest(requestDTO);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update a harvest", description = "Updates an existing harvest by ID")
    @ApiResponse(responseCode = "200", description = "Harvest updated successfully")
    public ResponseEntity<HarvestDTO> updateHarvest(
            @PathVariable Long id,
            @Valid @RequestBody HarvestRequestDTO requestDTO) {
        log.debug("REST request to update Harvest : {}, {}", id, requestDTO);

        HarvestDTO harvestDTO = HarvestDTO.builder()
                .id(id)
                .harvestDate(requestDTO.getHarvestDate())
                .season(requestDTO.getSeason())
                .build();

        HarvestDTO result = harvestService.update(id, harvestDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    @Operation(summary = "Get all harvests", description = "Returns a page of harvests")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved harvests")
    public ResponseEntity<Page<HarvestDTO>> getAllHarvests(
            @Parameter(description = "Pagination information")
            Pageable pageable) {
        log.debug("REST request to get a page of Harvests");
        Page<HarvestDTO> page = harvestService.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/list")
    @Operation(summary = "Get all harvests as list", description = "Returns all harvests as a list")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved harvests list")
    public ResponseEntity<List<HarvestDTO>> getAllHarvestsList() {
        log.debug("REST request to get all Harvests as list");
        List<HarvestDTO> harvests = harvestService.findAll();
        return ResponseEntity.ok(harvests);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a harvest by ID", description = "Returns a harvest by its ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the harvest")
    @ApiResponse(responseCode = "404", description = "Harvest not found")
    public ResponseEntity<HarvestDTO> getHarvest(@PathVariable Long id) {
        log.debug("REST request to get Harvest : {}", id);
        return harvestService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a harvest", description = "Deletes a harvest by ID")
    @ApiResponse(responseCode = "204", description = "Harvest deleted successfully")
    @ApiResponse(responseCode = "404", description = "Harvest not found")
    public ResponseEntity<Void> deleteHarvest(@PathVariable Long id) {
        log.debug("REST request to delete Harvest : {}", id);
        harvestService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    @Operation(summary = "Get harvest count", description = "Returns the total number of harvests")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved count")
    public ResponseEntity<Long> getHarvestCount() {
        log.debug("REST request to count Harvests");
        return ResponseEntity.ok(harvestService.count());
    }

    @PostMapping("/batch")
    @Operation(summary = "Create multiple harvests", description = "Creates multiple harvest records at once")
    @ApiResponse(responseCode = "201", description = "Harvests created successfully")
    public ResponseEntity<List<HarvestDTO>> createHarvests(
            @Valid @RequestBody List<HarvestRequestDTO> requestDTOs) {
        log.debug("REST request to save Harvests : {}", requestDTOs);

        List<HarvestDTO> harvestDTOs = requestDTOs.stream()
                .map(req -> HarvestDTO.builder()
                        .harvestDate(req.getHarvestDate())
                        .season(req.getSeason())
                        .build())
                .toList();

        List<HarvestDTO> result = harvestService.createAll(harvestDTOs);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("/batch")
    @Operation(summary = "Delete multiple harvests", description = "Deletes multiple harvests by their IDs")
    @ApiResponse(responseCode = "204", description = "Harvests deleted successfully")
    public ResponseEntity<Void> deleteHarvests(@RequestBody List<Long> ids) {
        log.debug("REST request to delete Harvests : {}", ids);
        List<HarvestDTO> harvestsToDelete = harvestService.findAllById(ids);
        harvestService.deleteAll(harvestsToDelete);
        return ResponseEntity.noContent().build();
    }
}