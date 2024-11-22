package ma.hariti.asmaa.wrm.citrontrack.service.farm;

import lombok.extern.slf4j.Slf4j;
import ma.hariti.asmaa.wrm.citrontrack.builder.FarmSpecificationBuilder;
import ma.hariti.asmaa.wrm.citrontrack.dto.farm.FarmDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.farm.FarmRequestDTO;
import ma.hariti.asmaa.wrm.citrontrack.entity.Farm;
import ma.hariti.asmaa.wrm.citrontrack.mapper.FarmMapper;
import ma.hariti.asmaa.wrm.citrontrack.repository.FarmRepository;
import ma.hariti.asmaa.wrm.citrontrack.util.GenericDtoServiceImpl;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class FarmServiceImpl extends GenericDtoServiceImpl<FarmDTO, Farm, Long> implements FarmService {

    private final FarmRepository farmRepository;
    private final FarmMapper farmMapper;

    public FarmServiceImpl(FarmRepository farmRepository, FarmMapper farmMapper) {
        super(farmRepository);
        this.farmRepository = farmRepository;
        this.farmMapper = farmMapper;
    }

    @Override
    protected FarmDTO toDto(Farm entity) {
        return farmMapper.toDto(entity);
    }

    @Override
    protected Farm toEntity(FarmDTO dto) {
        return farmMapper.toEntity(dto);
    }

    @Override
    public void updateEntity(Farm entity, FarmDTO dto) {
        farmMapper.updateEntityFromDto(dto, entity);
    }

    public FarmDTO createFromRequest(FarmRequestDTO requestDTO) {
        log.debug("Creating Farm from FarmRequestDTO: {}", requestDTO);
        Farm entity = farmMapper.toEntity(requestDTO);
        Farm savedEntity = farmRepository.save(entity);
        return farmMapper.toDto(savedEntity);
    }
    public List<FarmDTO> searchFarms(FarmSpecificationBuilder builder) {
        Specification<Farm> spec = builder.build();
        List<Farm> farms = farmRepository.findAll(spec);
        return farms.stream()
                .map(farmMapper::toDto)
                .collect(Collectors.toList());
    }
}
