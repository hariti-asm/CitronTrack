package ma.hariti.asmaa.wrm.citrontrack.service.field;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@Validated
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

        if (dto.getArea() != null) {
            var farm = farmRepository.findById(entity.getFarm().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Farm not found with id: " + entity.getFarm().getId()));

            if (dto.getArea() > farm.getTotalArea() / 2) {
                throw new IllegalArgumentException("Field area must be less than or equal to half of the farm's total area: " + farm.getTotalArea());
            }
        }
    }


    @Override
    @Transactional
    public FieldDTO update(Long id, FieldDTO dto) {
        log.debug("Updating {} with id: {}", dtoClass.getSimpleName(), id);

        if (!existsById(id)) {
            throw new EntityNotFoundException(
                    String.format("%s not found with id: %s", dtoClass.getSimpleName(), id)
            );
        }

        Field existingEntity = fieldRepository.findById(id).orElseThrow();

        validateFieldArea(dto);

        fieldMapper.updateEntityFromDto(dto, existingEntity);

        return toDto(fieldRepository.save(existingEntity));
    }

    private void validateFieldArea(FieldDTO dto) {
        Field existingField = fieldRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Field not found with id: " + dto.getId()));

        var farm = farmRepository.findById(existingField.getFarm().getId())
                .orElseThrow(() -> new EntityNotFoundException("Farm not found with id: " + existingField.getFarm().getId()));

        if (dto.getArea() > farm.getTotalArea() / 2) {
            throw new IllegalArgumentException("Field area must be less than or equal to half of the farm's total area: " + farm.getTotalArea());
        }
    }
    @Override
    public FieldResponseDTO createFromRequest(FieldRequestDTO requestDTO) {
        var farm = farmRepository.findById(requestDTO.getFarmId())
                .orElseThrow(() -> new EntityNotFoundException("Farm not found with id: " + requestDTO.getFarmId()));

        long existingFieldCount = fieldRepository.countByFarmId(requestDTO.getFarmId());
        if (existingFieldCount >= 10) {
            throw new IllegalStateException("Farm cannot have more than 10 fields");
        }

        if (requestDTO.getArea() > farm.getTotalArea() / 2) {
            throw new IllegalArgumentException("Field area must be less than or equal to half of the farm's total area: " + farm.getTotalArea());
        }

        Field entity = fieldMapper.toEntity(requestDTO);
        Field savedEntity = fieldRepository.save(entity);
        return fieldMapper.toResponseDto(savedEntity);
    }

    @Override
    @Transactional
    public Optional<FieldResponseDTO> findByIdWithResponse(Long id) {
        Field field = fieldRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Field not found with id: " + id));

        FieldResponseDTO fieldResponseDTO = fieldMapper.toResponseDto(field);

        return Optional.of(fieldResponseDTO);
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
