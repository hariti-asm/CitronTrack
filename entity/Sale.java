package ma.hariti.asmaa.wrm.citrontrack.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "sales")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "Unit price must be positive")
    private double unitPrice;

    @Positive(message = "Quantity sold must be zero or positive")
    private double quantitySold;

    @NotNull(message = "Date sold cannot be null")
    @PastOrPresent(message = "Date sold must be in the past or present")
    private LocalDate dateSold;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "harvest_id")
    private Harvest harvest;
    @Transient
    private Double revenue;
}
