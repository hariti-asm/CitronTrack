package ma.hariti.asmaa.wrm.citrontrack.dto.sale;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.hariti.asmaa.wrm.citrontrack.dto.customer.CustomerDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.harvest.HarvestDTO;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleDTO {

    private Long id;

    @NotNull(message = "Unit price cannot be null")
    @Positive(message = "Unit price must be greater than 0")
    private double unitPrice;

    @NotNull(message = "Quantity sold cannot be null")
    @Positive(message = "Quantity sold must be greater than 0")
    private double quantitySold;

    @NotNull(message = "Date sold cannot be null")
    @PastOrPresent(message = "Date sold must be in the past or present")
    private LocalDate dateSold;

    @NotNull(message = "Customer cannot be null")
    private CustomerDTO customer;

    @NotNull(message = "Harvest cannot be null")
    private HarvestDTO harvest;
    private double revenue;

}
