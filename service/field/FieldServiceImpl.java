package ma.hariti.asmaa.wrm.citrontrack.service.field;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import ma.hariti.asmaa.wrm.citrontrack.dto.field.FieldDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.field.FieldRequestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.field.FieldResponseDTO;
import ma.hariti.asmaa.wrm.citrontrack.entity.Field;
import ma.hariti.asmaa.wrm.citrontrack.mapper.FieldMapper;
import ma.hariti.asmaa.wrm.citrontrack.repository.FarmRepository;
import ma.hariti.asmaa.wrm.citrontrack.repository.FieldRepository;
import ma.hariti.asmaa.wrm.citrontrack.util.GenericDtoServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class FieldServiceImpl extends GenericDtoServiceImpl<FieldDTO, Field, Long> implements FieldService {

    private final FieldRepository fieldRepository;
    private final FieldMapper fieldMapper;
    private final FarmRepository farmRepository;

    public FieldServiceImpl(FieldRepository fieldRepository, FieldMapper fieldMapper, FarmRepository farmRepository) {
        super(fieldRepository);
        this.fieldRepository = fieldRepository;
        this.fieldMapper = fieldMapper;
        this.farmRepository = farmRepository;
    }

    @Override
    protected FieldDTO toDto(Field entity) {
        return fieldMapper.toDto(entity);
    }

    @Override
    protected Field toEntity(FieldDTO dto) {
        Field field = new Field();
        field.setArea(dto.getArea());
        return field;
    }
    @Override
    protected void updateEntity(Field entity, FieldDTO dto) {
        fieldMapper.updateEntityFromDto(dto, entity);
    }
    @Override
    public FieldResponseDTO createFromRequest(FieldRequestDTO requestDTO) {
        farmRepository.findById(requestDTO.getFarmId())
                .orElseThrow(() -> new EntityNotFoundException("Farm not found with id: " + requestDTO.getFarmId()));

        Field entity = fieldMapper.toEntity(requestDTO);
        Field savedEntity = fieldRepository.save(entity);
        return fieldMapper.toResponseDto(savedEntity);
    }

    @Override
    public Optional<FieldResponseDTO> findByIdWithResponse(Long id) {
        return Optional.empty();
    }

    @Override
    @Transactional()
    public Page<FieldResponseDTO> findAllWithResponse(Pageable pageable) {
        return fieldRepository.findAll(pageable)
                .map(fieldMapper::toResponseDto);
    }


    @Transactional()
    public List<FieldResponseDTO> findAllWithResponse() {
        return fieldRepository.findAll().stream()
                .map(fieldMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
