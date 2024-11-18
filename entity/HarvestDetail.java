package ma.hariti.asmaa.wrm.citrontrack.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "harvest_details")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HarvestDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double quantity;

    @ManyToOne
    @JoinColumn(name = "tree_id", nullable = false)
    private Tree tree;

    @ManyToOne
    @JoinColumn(name = "harvest_id", nullable = false)
    private Harvest harvest;
}
