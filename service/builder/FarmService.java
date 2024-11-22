package ma.hariti.asmaa.wrm.citrontrack.service.builder;

import ma.hariti.asmaa.wrm.citrontrack.builder.FarmSpecificationBuilder;
import ma.hariti.asmaa.wrm.citrontrack.entity.Farm;
import ma.hariti.asmaa.wrm.citrontrack.repository.FarmRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;

public class FarmService {
    private final FarmRepository farmRepository;

    public FarmService(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
    }

    public List<Farm> searchFarms(FarmSpecificationBuilder builder) {
        Specification<Farm> spec = builder.build();
        return farmRepository.findAll((Sort) spec);
    }

}