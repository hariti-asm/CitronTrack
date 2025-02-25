package ma.hariti.asmaa.wrm.citrontrack.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ma.hariti.asmaa.wrm.citrontrack.enums.Season;

import java.time.LocalDate;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Harvests")
public class Harvest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Harvest date cannot be null")
    @PastOrPresent(message = "Harvest date must be in the past or present")
    private LocalDate harvestDate;

    @NotNull(message = "Season cannot be null")
    @Enumerated(EnumType.STRING)
    private Season season;

    @Transient
    private double totalQuantity;

    @OneToMany(mappedBy = "harvest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HarvestDetail> harvestDetails;


}
