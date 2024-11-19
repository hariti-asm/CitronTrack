package ma.hariti.asmaa.wrm.citrontrack.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import ma.hariti.asmaa.wrm.citrontrack.dto.ApiResponseDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.farm.FarmDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.farm.FarmResponseDTO;
import ma.hariti.asmaa.wrm.citrontrack.service.farm.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/farms")
public class FarmController {

    private final FarmService farmService;

    @Autowired
    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    @PostMapping
    @Operation(summary = "Create a new farm", description = "Creates and returns the newly created farm")
    @ApiResponse(responseCode = "200", description = "Farm successfully created")
    public ResponseEntity<FarmDTO> createFarm(@RequestBody FarmDTO farmDTO) {
        log.debug("REST request to create Farm : {}", farmDTO);
        FarmDTO createdFarm = farmService.create(farmDTO);
        return ResponseEntity.ok(createdFarm);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a farm", description = "Updates the details of an existing farm by its ID")
    @ApiResponse(responseCode = "200", description = "Farm successfully updated")
    @ApiResponse(responseCode = "404", description = "Farm not found")
    public ResponseEntity<FarmDTO> updateFarm(@PathVariable Long id, @RequestBody FarmDTO farmDTO) {
        log.debug("REST request to update Farm with ID : {}", id);
        FarmDTO updatedFarm = farmService.update(id, farmDTO);
        return ResponseEntity.ok(updatedFarm);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a farm", description = "Deletes a farm by its ID")
    @ApiResponse(responseCode = "204", description = "Farm successfully deleted")
    @ApiResponse(responseCode = "404", description = "Farm not found")
    public ResponseEntity<Void> deleteFarm(@PathVariable Long id) {
        log.debug("REST request to delete Farm with ID : {}", id);
        farmService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a farm by ID", description = "Returns a farm by its ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the farm")
    @ApiResponse(responseCode = "404", description = "Farm not found")
    public ResponseEntity<ApiResponseDTO<FarmDTO>> getFarmById(@PathVariable Long id) {
        log.debug("REST request to get Farm : {}", id);
        return farmService.findById(id)
                .map(farmDTO -> ResponseEntity.ok(ApiResponseDTO.success(farmDTO)))
                .orElse(ResponseEntity.status(404).body(ApiResponseDTO.error("Farm not found", 404)));
    }



    @GetMapping
    @Operation(summary = "Get all farms", description = "Returns a list of all farms")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of farms")
    public ResponseEntity<Iterable<FarmDTO>> getAllFarms() {
        log.debug("REST request to get all Farms");
        return ResponseEntity.ok(farmService.findAll());
    }
}
