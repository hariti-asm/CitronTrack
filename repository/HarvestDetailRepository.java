package ma.hariti.asmaa.wrm.citrontrack.repository;

import ma.hariti.asmaa.wrm.citrontrack.embeddedable.HarvestDetailId;
import ma.hariti.asmaa.wrm.citrontrack.entity.HarvestDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HarvestDetailRepository extends JpaRepository<HarvestDetail, HarvestDetailId> {
}
