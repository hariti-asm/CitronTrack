package ma.hariti.asmaa.wrm.citrontrack.service.harvestDetail;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import ma.hariti.asmaa.wrm.citrontrack.dto.harvestDetail.HarvestDetailRequestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.harvestDetail.HarvestDetailResponseDTO;
import ma.hariti.asmaa.wrm.citrontrack.embeddedable.HarvestDetailId;
import ma.hariti.asmaa.wrm.citrontrack.entity.Harvest;
import ma.hariti.asmaa.wrm.citrontrack.entity.HarvestDetail;
import ma.hariti.asmaa.wrm.citrontrack.entity.Tree;
import ma.hariti.asmaa.wrm.citrontrack.enums.TreeProductivity;
import ma.hariti.asmaa.wrm.citrontrack.mapper.HarvestDetailMapper;
import ma.hariti.asmaa.wrm.citrontrack.repository.HarvestDetailRepository;
import ma.hariti.asmaa.wrm.citrontrack.repository.HarvestRepository;
import ma.hariti.asmaa.wrm.citrontrack.repository.TreeRepository;
import ma.hariti.asmaa.wrm.citrontrack.util.GenericDtoServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class HarvestDetailServiceImpl extends GenericDtoServiceImpl<HarvestDetailRequestDTO, HarvestDetail, HarvestDetailId>
        implements HarvestDetailService {

    private final HarvestDetailRepository harvestDetailRepository;
    private final HarvestDetailMapper harvestDetailMapper;
    private final TreeRepository treeRepository;
    private final HarvestRepository harvestRepository;

    public HarvestDetailServiceImpl(
            HarvestDetailRepository harvestDetailRepository,
            HarvestDetailMapper harvestDetailMapper,
            TreeRepository treeRepository,
            HarvestRepository harvestRepository
    ) {
        super(harvestDetailRepository);
        this.harvestDetailRepository = harvestDetailRepository;
        this.harvestDetailMapper = harvestDetailMapper;
        this.treeRepository = treeRepository;
        this.harvestRepository = harvestRepository;
    }

    @Override
    protected HarvestDetailRequestDTO toDto(HarvestDetail entity) {
        return null;
    }

    @Override
    protected HarvestDetail toEntity(HarvestDetailRequestDTO dto) {
        return harvestDetailMapper.toEntityFromRequest(dto);
    }

    @Override
    protected void updateEntity(HarvestDetail entity, HarvestDetailRequestDTO dto) {
        harvestDetailMapper.updateEntityFromDto(dto, entity);
    }

    @Override
    @Transactional
    public HarvestDetailResponseDTO createFromRequest(HarvestDetailRequestDTO requestDTO) {
        Tree tree = treeRepository.findById(requestDTO.getTreeId())
                .orElseThrow(() -> new EntityNotFoundException("Tree not found"));

        Harvest harvest = harvestRepository.findById(requestDTO.getHarvestId())
                .orElseThrow(() -> new EntityNotFoundException("Harvest not found"));

        boolean alreadyHarvested = harvestDetailRepository.existsByTreeAndHarvestSeason(tree, harvest.getSeason());
        if (alreadyHarvested) {
            throw new IllegalStateException("Tree cannot be harvested twice in the same season");
        }

        LocalDate currentDate = LocalDate.now();
        int treeAge = Period.between(tree.getPlantingDate(), currentDate).getYears();

        tree.setProductivity(TreeProductivity.fromAge(treeAge));

        HarvestDetail entity = harvestDetailMapper.toEntityFromRequest(requestDTO);
        entity.setTree(tree);
        entity.setHarvest(harvest);

        HarvestDetail savedEntity = harvestDetailRepository.save(entity);
        return harvestDetailMapper.toResponseDto(savedEntity);
    }

    @Override
    @Transactional
    public HarvestDetailResponseDTO updateFromRequest(HarvestDetailId id, HarvestDetailRequestDTO requestDTO) {
        HarvestDetail existingEntity = harvestDetailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Harvest detail not found"));

        Tree tree = treeRepository.findById(requestDTO.getTreeId())
                .orElseThrow(() -> new EntityNotFoundException("Tree not found"));

        Harvest harvest = harvestRepository.findById(requestDTO.getHarvestId())
                .orElseThrow(() -> new EntityNotFoundException("Harvest not found"));

        harvestDetailMapper.updateEntityFromDto(requestDTO, existingEntity);
        existingEntity.setTree(tree);
        existingEntity.setHarvest(harvest);

        HarvestDetail updatedEntity = harvestDetailRepository.save(existingEntity);
        return harvestDetailMapper.toResponseDto(updatedEntity);
    }
}