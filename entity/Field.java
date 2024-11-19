package ma.hariti.asmaa.wrm.citrontrack.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import ma.hariti.asmaa.wrm.citrontrack.util.BaseEntity;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;
import lombok.*;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Fields")
public class Field extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotNull(message = "Area is required")
    @DecimalMin(value = "0.1", message = "Minimum area is 0.1 hectare")
    @Column
    private double area;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id")
    @NotNull(message = "Field must be associated with a farm")
    private Farm farm;


    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<Tree> trees = new ArrayList<>();

}

