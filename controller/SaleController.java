package ma.hariti.asmaa.wrm.citrontrack.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ma.hariti.asmaa.wrm.citrontrack.dto.sale.SaleDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.sale.SaleRequestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.sale.SaleResponseDTO;
import ma.hariti.asmaa.wrm.citrontrack.service.sale.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sales")
@Tag(name = "Sales", description = "API for managing sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    @Operation(summary = "Create a new sale", description = "Creates a new sale and returns the created sale.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Sale created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<SaleResponseDTO> createSale(@RequestBody SaleRequestDTO requestDTO) {
        SaleResponseDTO saleResponseDTO = saleService.createFromRequest(requestDTO);
        return ResponseEntity.ok(saleResponseDTO);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get a sale by ID", description = "Fetches a sale using its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sale found"),
            @ApiResponse(responseCode = "404", description = "Sale not found")
    })
    public ResponseEntity<SaleDTO> getSaleById(@PathVariable Long id) {
        Optional<SaleDTO> saleDTO = saleService.findById(id);
        return saleDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    @Operation(summary = "Get all sales", description = "Fetches all sales.")
    @ApiResponse(responseCode = "200", description = "List of sales retrieved")
    public ResponseEntity<List<SaleDTO>> getAllSales() {
        List<SaleDTO> sales = saleService.findAll();
        return ResponseEntity.ok(sales);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a sale by ID", description = "Updates an existing sale identified by its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sale updated successfully"),
            @ApiResponse(responseCode = "404", description = "Sale not found")
    })
    public ResponseEntity<SaleDTO> updateSale(@PathVariable Long id, @RequestBody SaleDTO saleDTO) {
        SaleDTO updatedSale = saleService.update(id, saleDTO);
        return ResponseEntity.ok(updatedSale);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a sale by ID", description = "Deletes a sale identified by its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Sale deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Sale not found")
    })
    public ResponseEntity<Void> deleteSaleById(@PathVariable Long id) {
        saleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/batch")
    @Operation(summary = "Create multiple sales", description = "Creates multiple sales at once.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Sales created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<List<SaleDTO>> createSales(@RequestBody List<SaleDTO> saleDTOs) {
        List<SaleDTO> createdSales = saleService.createAll(saleDTOs);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSales);
    }

    @DeleteMapping("/batch")
    @Operation(summary = "Delete multiple sales", description = "Deletes multiple sales at once.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Sales deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<Void> deleteSales(@RequestBody List<SaleDTO> saleDTOs) {
        saleService.deleteAll(saleDTOs);
        return ResponseEntity.noContent().build();
    }
}
