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
import ma.hariti.asmaa.wrm.citrontrack.mapper.HarvestMapper;
import ma.hariti.asmaa.wrm.citrontrack.service.HarvestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    @Operation(summary = "Create a new harvest", description = "Creates a new harvest record")
    @ApiResponse(responseCode = "201", description = "Harvest created successfully")
    public ResponseEntity<HarvestDTO> createHarvest(@Valid @RequestBody HarvestRequestDTO requestDTO) {
        log.debug("REST request to save Harvest : {}", requestDTO);

        HarvestDTO harvestDTO = HarvestDTO.builder()
                .harvestDate(requestDTO.getHarvestDate())
                .season(requestDTO.getSeason())
                .build();

        HarvestDTO result = harvestService.create(harvestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.getId())
                .toUri();

        return ResponseEntity.created(location).body(result);
    }


}