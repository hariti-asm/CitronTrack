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
        // Fetch the tree from the database
        Tree tree = treeRepository.findById(requestDTO.getTreeId())
                .orElseThrow(() -> new EntityNotFoundException("Tree not found"));

        // Fetch the harvest from the database
        Harvest harvest = harvestRepository.findById(requestDTO.getHarvestId())
                .orElseThrow(() -> new EntityNotFoundException("Harvest not found"));

        // Check if the tree has already been harvested in the same season
        boolean alreadyHarvested = harvestDetailRepository.existsByTreeAndHarvestSeason(tree, harvest.getSeason());
        if (alreadyHarvested) {
            throw new IllegalStateException("Tree cannot be harvested twice in the same season");
        }

        // Calculate tree age and set its productivity
        LocalDate currentDate = LocalDate.now();
        int treeAge = Period.between(tree.getPlantingDate(), currentDate).getYears();
        tree.setProductivity(TreeProductivity.fromAge(treeAge));

        // Convert the HarvestDetailRequestDTO to an entity
        HarvestDetail entity = harvestDetailMapper.toEntityFromRequest(requestDTO);
        entity.setTree(tree);
        entity.setHarvest(harvest);

        // Save the new HarvestDetail entity
        HarvestDetail savedEntity = harvestDetailRepository.save(entity);

        // Update the totalQuantity field in the Harvest entity
        updateHarvestTotalQuantity(harvest);

        // Flush the changes to make sure the updated harvest entity is persisted
        harvestRepository.flush();  // Force the entity manager to persist the changes immediately

        // Return the response DTO for the saved HarvestDetail
        return harvestDetailMapper.toResponseDto(savedEntity);
    }

    private void updateHarvestTotalQuantity(Harvest harvest) {
        double totalQuantity = harvest.getHarvestDetails().stream()
                .mapToDouble(HarvestDetail::getQuantity)
                .sum();

        harvest.setTotalQuantity(totalQuantity);

        harvestRepository.save(harvest);
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