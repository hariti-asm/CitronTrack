package ma.hariti.asmaa.wrm.citrontrack.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import ma.hariti.asmaa.wrm.citrontrack.embeddedable.HarvestDetailId;

@Entity
@Table(name = "harvest_details")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HarvestDetail {

    @EmbeddedId
    private HarvestDetailId id;

    @NotNull(message = "Quantity cannot be null")
    @Positive(message = "Quantity harvested by tree must be greater than 0")
    private Double quantity;

    @ManyToOne
    @MapsId("treeId")
    @JoinColumn(name = "tree_id", nullable = false)
    @NotNull(message = "Tree must not be null")
    private Tree tree;

    @ManyToOne
    @MapsId("harvestId")
    @JoinColumn(name = "harvest_id", nullable = false)
    @NotNull(message = "Harvest must not be null")
    private Harvest harvest;
}
