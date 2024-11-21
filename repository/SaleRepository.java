package ma.hariti.asmaa.wrm.citrontrack.repository;

import ma.hariti.asmaa.wrm.citrontrack.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository  extends JpaRepository<Sale, Long> {
    List<Sale> findByHarvestId(Long harvestId);

}

