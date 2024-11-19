package ma.hariti.asmaa.wrm.citrontrack.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import ma.hariti.asmaa.wrm.citrontrack.enums.TreeProductivity;

import java.time.LocalDate;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Trees")
public class Tree {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Plantation date cannot be null")
    @PastOrPresent(message = "Planting date must be in the past or present")
    private LocalDate platingDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id")
    private Field field;

    @Transient
    private
    TreeProductivity productivity;

    @OneToMany(mappedBy = "tree", fetch = FetchType.LAZY)
    private List<HarvestDetail> harvestDetails;

}

