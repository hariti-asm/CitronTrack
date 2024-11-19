package ma.hariti.asmaa.wrm.citrontrack.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import ma.hariti.asmaa.wrm.citrontrack.dto.field.FieldDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.field.FieldRequestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.field.FieldResponseDTO;
import ma.hariti.asmaa.wrm.citrontrack.service.field.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/fields")
public class FieldController {

    private final FieldService fieldService;

    public FieldController(FieldService fieldService) {
        this.fieldService = fieldService;
    }


    @PostMapping
    @Operation(summary = "Create a new field", description = "Creates a new field for a given farm")
    @ApiResponse(responseCode = "200", description = "Successfully created the field")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    public ResponseEntity<FieldResponseDTO> createField(@Valid @RequestBody FieldRequestDTO fieldRequestDTO) {
        log.debug("REST request to create Field: {}", fieldRequestDTO);
        FieldResponseDTO createdField = fieldService.createFromRequest(fieldRequestDTO);
        return ResponseEntity.ok(createdField);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing field", description = "Updates the field with the given ID")
    @ApiResponse(responseCode = "200", description = "Successfully updated the field")
    @ApiResponse(responseCode = "404", description = "Field not found")
    public ResponseEntity<FieldDTO> updateField(@PathVariable Long id, @Valid @RequestBody FieldDTO fieldDTO) {
        FieldDTO updatedField = fieldService.update(id, fieldDTO);
        return ResponseEntity.ok(updatedField);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a field by ID", description = "Deletes a field by its ID")
    @ApiResponse(responseCode = "204", description = "Successfully deleted the field")
    @ApiResponse(responseCode = "404", description = "Field not found")
    public ResponseEntity<Void> deleteField(@PathVariable Long id) {
        fieldService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a field by id")
    public ResponseEntity<FieldResponseDTO> getField(@PathVariable Long id) {
        log.debug("REST request to get Field : {}", id);
        return fieldService.findByIdWithResponse(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Field not found with id: %d", id)
                ));
    }
    @GetMapping
    @Operation(summary = "Get all fields", description = "Retrieves fields with pagination")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved fields")
    public ResponseEntity<Page<FieldResponseDTO>> getAllFields(
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.debug("REST request to get Fields page: {}", pageable);
        Page<FieldResponseDTO> fields = fieldService.findAllWithResponse(pageable);
        return ResponseEntity.ok(fields);
    }
}
