package ma.hariti.asmaa.wrm.citrontrack.service.tree;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import ma.hariti.asmaa.wrm.citrontrack.dto.tree.TreeDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.tree.TreeRequestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.tree.TreeResponseDTO;
import ma.hariti.asmaa.wrm.citrontrack.entity.Field;
import ma.hariti.asmaa.wrm.citrontrack.entity.Tree;
import ma.hariti.asmaa.wrm.citrontrack.enums.TreeProductivity;
import ma.hariti.asmaa.wrm.citrontrack.mapper.TreeMapper;
import ma.hariti.asmaa.wrm.citrontrack.repository.FieldRepository;
import ma.hariti.asmaa.wrm.citrontrack.repository.TreeRepository;
import ma.hariti.asmaa.wrm.citrontrack.util.GenericDtoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class TreeServiceImpl extends GenericDtoServiceImpl<TreeDTO, Tree, Long> implements TreeService {

    private static final Logger log = LoggerFactory.getLogger(TreeServiceImpl.class);
    private final TreeRepository treeRepository;
    private final FieldRepository fieldRepository;
    private final TreeMapper treeMapper;

    public TreeServiceImpl(TreeRepository treeRepository, FieldRepository fieldRepository, TreeMapper treeMapper) {
        super(treeRepository);
        this.treeRepository = treeRepository;
        this.fieldRepository = fieldRepository;
        this.treeMapper = treeMapper;
    }

    @Override
    protected TreeDTO toDto(Tree entity) {
        return treeMapper.toDto(entity);
    }

    @Override
    protected Tree toEntity(TreeDTO dto) {
        return treeMapper.toEntity(dto);
    }

    @Override
    protected void updateEntity(Tree entity, TreeDTO dto) {
        treeMapper.updateEntityFromDto(dto, entity);

        if (dto.getPlantingDate() != null && dto.getPlantingDate().isAfter(java.time.LocalDate.now())) {
            throw new IllegalArgumentException("Planting date cannot be in the future.");
        }
    }

    @Override
    public TreeResponseDTO createFromRequest(TreeRequestDTO requestDTO) {
        var field = fieldRepository.findById(requestDTO.getFieldId())
                .orElseThrow(() -> new EntityNotFoundException("Field not found with id: " + requestDTO.getFieldId()));

        Tree entity = treeMapper.toEntityFromRequest(requestDTO);
        entity.setField(field);

        LocalDate currentDate = LocalDate.now();
        int treeAge = Period.between(requestDTO.getPlantingDate(), currentDate).getYears();
        entity.setProductivity(TreeProductivity.fromAge(treeAge));

        Tree savedEntity = treeRepository.save(entity);
        return treeMapper.toResponseDto(savedEntity);
    }

    @Override
    public Optional<TreeResponseDTO> findByIdWithResponse(Long id) {
        return treeRepository.findById(id).map(tree -> {
            LocalDate currentDate = LocalDate.now();
            int treeAge = Period.between(tree.getPlantingDate(), currentDate).getYears();

            tree.setProductivity(TreeProductivity.fromAge(treeAge));

            return treeMapper.toResponseDto(tree);
        });
    }


    public Page<TreeResponseDTO> findAllWithResponse(Pageable pageable) {
        Page<Tree> treesPage = treeRepository.findAll(pageable);

        return treesPage.map(treeMapper::toResponseDto);
    }

    @Transactional
    public TreeResponseDTO updateFromRequest(Long id, TreeRequestDTO requestDTO) {
        Tree existingTree = treeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tree not found with id: " + id));

        Field field = fieldRepository.findById(requestDTO.getFieldId())
                .orElseThrow(() -> new EntityNotFoundException("Field not found with id: " + requestDTO.getFieldId()));

        if (requestDTO.getPlantingDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Planting date cannot be in the future.");
        }

        TreeDTO treeDTO = TreeDTO.builder()
                .id(id)
                .plantingDate(requestDTO.getPlantingDate())
                .fieldId(requestDTO.getFieldId())
                .build();

        treeMapper.updateEntityFromDto(treeDTO, existingTree);

        existingTree.setField(field);

        int treeAge = Period.between(requestDTO.getPlantingDate(), LocalDate.now()).getYears();
        existingTree.setProductivity(TreeProductivity.fromAge(treeAge));

        // Save and return
        Tree updatedTree = treeRepository.save(existingTree);
        return treeMapper.toResponseDto(updatedTree);
    }
}