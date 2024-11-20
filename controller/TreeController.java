package ma.hariti.asmaa.wrm.citrontrack.controller;

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
public class TreeController {

    private final TreeService treeService;

    public TreeController(TreeService treeService) {
        this.treeService = treeService;
    }

    @PostMapping
    public ResponseEntity<TreeResponseDTO> createTree(@Valid @RequestBody TreeRequestDTO treeRequestDTO) {
        TreeResponseDTO createdTree = treeService.createFromRequest(treeRequestDTO);
        return new ResponseEntity<>(createdTree, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreeResponseDTO> getTreeById(@PathVariable Long id) {
        return treeService.findByIdWithResponse(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<TreeResponseDTO>> getAllTrees(Pageable pageable) {
        return ResponseEntity.ok(treeService.findAllWithResponse(pageable));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<TreeResponseDTO>> getAllTreesList(Pageable pageable) {
        Page<TreeResponseDTO> treePage = treeService.findAllWithResponse(pageable);
        return ResponseEntity.ok(treePage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreeResponseDTO> updateTree(
            @PathVariable Long id,
            @Valid @RequestBody TreeRequestDTO treeRequestDTO) {
        TreeResponseDTO updatedTree = treeService.updateFromRequest(id, treeRequestDTO);
        return ResponseEntity.ok(updatedTree);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTree(@PathVariable Long id) {
        treeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}