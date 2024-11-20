package ma.hariti.asmaa.wrm.citrontrack.service.tree;

import jakarta.persistence.EntityNotFoundException;
import ma.hariti.asmaa.wrm.citrontrack.dto.tree.TreeDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.tree.TreeRequestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.tree.TreeResponseDTO;
import ma.hariti.asmaa.wrm.citrontrack.entity.Tree;
import ma.hariti.asmaa.wrm.citrontrack.mapper.TreeMapper;
import ma.hariti.asmaa.wrm.citrontrack.repository.FieldRepository;
import ma.hariti.asmaa.wrm.citrontrack.repository.TreeRepository;
import ma.hariti.asmaa.wrm.citrontrack.util.GenericDtoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        Tree savedEntity = treeRepository.save(entity);
        return treeMapper.toResponseDto(savedEntity);
    }

    @Override
    public Optional<TreeResponseDTO> findByIdWithResponse(Long id) {
        return treeRepository.findById(id)
                .map(treeMapper::toResponseDto);
    }

    @Transactional
    public Page<TreeResponseDTO> findAllWithResponse(Pageable pageable) {
        return treeRepository.findAll(pageable)
                .map(treeMapper::toResponseDto);
    }

    @Transactional
    public List<TreeResponseDTO> findAllWithResponse() {
        return treeRepository.findAll().stream()
                .map(treeMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}