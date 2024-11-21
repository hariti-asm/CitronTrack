package ma.hariti.asmaa.wrm.citrontrack.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private Long id;
    @NotBlank(message = "Customer's name is required")
    private String fullName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Customer's email is required")
    private String email;
}