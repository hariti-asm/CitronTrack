package ma.hariti.asmaa.wrm.citrontrack.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Farms")
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "location is required")
    private String location;

    @NotNull(message = "Total area is required")
    @DecimalMin(value = "0.1", message = "Minimum area is 0.1 hectare")
    @Column
    private double totalArea;

    @PastOrPresent(message = "Creation date must be in the past or present")
    private LocalDate creationDate;


    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Size(max = 10, message = "A farm cannot have more than 10 fields")
    private List<Field> fields = new ArrayList<>();

}
