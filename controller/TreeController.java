package ma.hariti.asmaa.wrm.citrontrack.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import ma.hariti.asmaa.wrm.citrontrack.dto.tree.TreeRequestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.tree.TreeResponseDTO;
import ma.hariti.asmaa.wrm.citrontrack.service.tree.TreeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trees")
@Tag(name = "Tree Management", description = "APIs for managing trees")
public class TreeController {

    private final TreeService treeService;

    public TreeController(TreeService treeService) {
        this.treeService = treeService;
    }

    @PostMapping
    @Operation(summary = "Create a new tree", description = "Creates a new tree record with the provided details.")
    @ApiResponse(responseCode = "201", description = "Tree successfully created.")
    public ResponseEntity<TreeResponseDTO> createTree(
            @Valid @RequestBody @Parameter(description = "Tree data for the new tree") TreeRequestDTO treeRequestDTO) {
        TreeResponseDTO createdTree = treeService.createFromRequest(treeRequestDTO);
        return new ResponseEntity<>(createdTree, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get tree by ID", description = "Fetches the details of a tree by its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tree found and returned."),
            @ApiResponse(responseCode = "404", description = "Tree not found.")
    })
    public ResponseEntity<TreeResponseDTO> getTreeById(
            @PathVariable @Parameter(description = "ID of the tree to retrieve") Long id) {
        return treeService.findByIdWithResponse(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Get all trees (paged)", description = "Fetches a paginated list of all trees.")
    @ApiResponse(responseCode = "200", description = "Paginated list of trees returned.")
    public ResponseEntity<Page<TreeResponseDTO>> getAllTrees(
            @Parameter(description = "Pagination information") Pageable pageable) {
        return ResponseEntity.ok(treeService.findAllWithResponse(pageable));
    }

    @GetMapping("/list")
    @Operation(summary = "Get all trees (alternative endpoint)", description = "Fetches a paginated list of all trees using an alternative endpoint.")
    @ApiResponse(responseCode = "200", description = "Paginated list of trees returned.")
    public ResponseEntity<Page<TreeResponseDTO>> getAllTreesList(
            @Parameter(description = "Pagination information") Pageable pageable) {
        Page<TreeResponseDTO> treePage = treeService.findAllWithResponse(pageable);
        return ResponseEntity.ok(treePage);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update tree details", description = "Updates the details of an existing tree.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tree successfully updated."),
            @ApiResponse(responseCode = "404", description = "Tree not found.")
    })
    public ResponseEntity<TreeResponseDTO> updateTree(
            @PathVariable @Parameter(description = "ID of the tree to update") Long id,
            @Valid @RequestBody @Parameter(description = "Updated tree data") TreeRequestDTO treeRequestDTO) {
        TreeResponseDTO updatedTree = treeService.updateFromRequest(id, treeRequestDTO);
        return ResponseEntity.ok(updatedTree);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete tree", description = "Deletes an existing tree by its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Tree successfully deleted."),
            @ApiResponse(responseCode = "404", description = "Tree not found.")
    })
    public ResponseEntity<Void> deleteTree(
            @PathVariable @Parameter(description = "ID of the tree to delete") Long id) {
        treeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
