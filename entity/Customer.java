package ma.hariti.asmaa.wrm.citrontrack.entity;


import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Embeddable
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @NotBlank(message = "Customer's name is required")
    private String fullName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Customer's email is required")
    private String email;
}
