package ma.hariti.asmaa.wrm.citrontrack.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    @NotBlank(message = "Customer's name is required")
    private String fullName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Customer's email is required")
    private String email;
}