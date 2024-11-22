package ma.hariti.asmaa.wrm.citrontrack.service.harvest;

import lombok.extern.slf4j.Slf4j;
import ma.hariti.asmaa.wrm.citrontrack.dto.harvest.HarvestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.harvest.HarvestRequestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.harvest.HarvestResponseDTO;
import ma.hariti.asmaa.wrm.citrontrack.entity.Harvest;
import ma.hariti.asmaa.wrm.citrontrack.mapper.HarvestMapper;
import ma.hariti.asmaa.wrm.citrontrack.repository.HarvestRepository;
import ma.hariti.asmaa.wrm.citrontrack.util.GenericDtoServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class HarvestServiceImpl extends GenericDtoServiceImpl<HarvestDTO, Harvest, Long>
        implements HarvestService {

    private final HarvestMapper harvestMapper;
    private final HarvestRepository harvestRepository;

    public HarvestServiceImpl(HarvestRepository harvestRepository, HarvestMapper harvestMapper) {
        super(harvestRepository);
        this.harvestMapper = harvestMapper;
        this.harvestRepository = harvestRepository;
    }

    @Override
    protected HarvestDTO toDto(Harvest entity) {
        return harvestMapper.toDTO(entity);
    }

    @Override
    protected Harvest toEntity(HarvestDTO dto) {
        return harvestMapper.toEntity(dto);
    }

    @Override
    public void updateEntity(Harvest entity, HarvestDTO dto) {
        harvestMapper.updateEntityFromDto(dto, entity);
    }

    public HarvestResponseDTO createHarvest(HarvestRequestDTO requestDTO) {
        Harvest harvest = harvestMapper.requestDtoToEntity(requestDTO);
        Harvest savedHarvest = harvestRepository.save(harvest);
        return harvestMapper.entityToResponseDto(savedHarvest);
    }

    private HarvestResponseDTO createHarvestResponseDto(Harvest harvest) {
        return harvestMapper.entityToResponseDto(harvest);
    }


}