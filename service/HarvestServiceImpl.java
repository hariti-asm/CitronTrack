package ma.hariti.asmaa.wrm.citrontrack.service;

import lombok.extern.slf4j.Slf4j;
import ma.hariti.asmaa.wrm.citrontrack.dto.harvest.HarvestDTO;
import ma.hariti.asmaa.wrm.citrontrack.entity.Harvest;
import ma.hariti.asmaa.wrm.citrontrack.mapper.HarvestMapper;
import ma.hariti.asmaa.wrm.citrontrack.repository.HarvestRepository;
import ma.hariti.asmaa.wrm.citrontrack.util.GenericDtoServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class HarvestServiceImpl extends GenericDtoServiceImpl<HarvestDTO, Harvest, Long> implements HarvestService {

    private final HarvestMapper harvestMapper;

    public HarvestServiceImpl(HarvestRepository repository, HarvestMapper harvestMapper) {
        super(repository);
        this.harvestMapper = harvestMapper;
    }

    @Override
    protected HarvestDTO toDto(Harvest entity) {
        return harvestMapper.toDto(entity);
    }

    @Override
    protected Harvest toEntity(HarvestDTO dto) {
        return harvestMapper.toEntity(dto);
    }

    @Override
    protected void updateEntity(Harvest entity, HarvestDTO dto) {
        harvestMapper.updateEntityFromDto(dto, entity);
    }
}