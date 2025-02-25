package ma.hariti.asmaa.wrm.citrontrack.repository;

import ma.hariti.asmaa.wrm.citrontrack.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldRepository  extends JpaRepository<Field, Long> {
    long countByFarmId(Long farmId);

}
