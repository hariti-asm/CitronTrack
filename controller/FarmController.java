package ma.hariti.asmaa.wrm.citrontrack.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import ma.hariti.asmaa.wrm.citrontrack.builder.FarmSpecificationBuilder;
import ma.hariti.asmaa.wrm.citrontrack.dto.ApiResponseDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.farm.FarmDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.farm.FarmRequestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.farm.FarmResponseDTO;
import ma.hariti.asmaa.wrm.citrontrack.service.farm.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
    public ResponseEntity<FarmDTO> createFarm(@RequestBody FarmRequestDTO farmRequestDTO) {
        log.debug("REST request to create Farm : {}", farmRequestDTO);
        FarmDTO createdFarm = farmService.createFromRequest(farmRequestDTO);
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
    @GetMapping("/search")
    @Operation(summary = "Search farms with dynamic criteria", description = "Search farms using flexible search parameters")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved matching farms")
    public ResponseEntity<List<FarmDTO>> searchFarms(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Double minArea,
            @RequestParam(required = false) Double maxArea,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdAfter,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdBefore,
            @RequestParam(required = false) Integer minFields
    ) {
        log.debug("REST request to search Farms with criteria");

        FarmSpecificationBuilder specBuilder = new FarmSpecificationBuilder()
                .name(name)
                .location(location)
                .minArea(minArea)
                .maxArea(maxArea)
                .withMinFields(minFields);

        List<FarmDTO> farms = farmService.searchFarms(specBuilder);
        return ResponseEntity.ok(farms);
    }
}
