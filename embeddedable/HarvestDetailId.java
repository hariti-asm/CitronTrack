package ma.hariti.asmaa.wrm.citrontrack.embeddedable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class HarvestDetailId implements Serializable {
    @NotNull(message = "Harvest ID cannot be null")
    @Column(name = "harvest_id")
    private Long harvestId;

    @NotNull(message = "Tree ID cannot be null")
    @Column(name = "tree_id")
    private Long treeId;
}