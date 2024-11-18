package ma.hariti.asmaa.wrm.citrontrack.dto.sale;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.hariti.asmaa.wrm.citrontrack.dto.customer.CustomerDTO;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleRequestDTO {

    @Positive(message = "Unit price must be positive")
    private double unitPrice;

    @Positive(message = "Quantity sold must be zero or positive")
    private double quantitySold;

    @NotNull(message = "Date sold cannot be null")
    @PastOrPresent(message = "Date sold must be in the past or present")
    private LocalDate dateSold;

    private CustomerDTO customer;

    private Long harvestId;
}
