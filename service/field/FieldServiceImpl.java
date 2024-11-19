package ma.hariti.asmaa.wrm.citrontrack.service.field;

import jakarta.persistence.EntityNotFoundException;
import ma.hariti.asmaa.wrm.citrontrack.dto.field.FieldDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.field.FieldResponseDTO;
import ma.hariti.asmaa.wrm.citrontrack.entity.Field;
import ma.hariti.asmaa.wrm.citrontrack.mapper.FieldMapper;
import ma.hariti.asmaa.wrm.citrontrack.repository.FieldRepository;
import ma.hariti.asmaa.wrm.citrontrack.util.GenericDtoServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class FieldServiceImpl extends GenericDtoServiceImpl<FieldDTO, Field, Long> {

    private final FieldRepository fieldRepository;
    private final FieldMapper fieldMapper;

    public FieldServiceImpl(FieldRepository fieldRepository, FieldMapper fieldMapper) {
        super(fieldRepository);
        this.fieldRepository = fieldRepository;
        this.fieldMapper = fieldMapper;
    }

    @Override
    protected FieldDTO toDto(Field entity) {
        return fieldMapper.toDto(entity);
    }

    @Override
    protected Field toEntity(FieldDTO dto) {
        return fieldMapper.toEntity(dto);
    }

    @Override
    protected void updateEntity(Field entity, FieldDTO dto) {
        fieldMapper.updateEntityFromDto(dto, entity);
    }

    @Override
    public FieldDTO create(FieldDTO dto) {
        Field field = fieldMapper.toEntity(dto);
        field = fieldRepository.save(field);
        return fieldMapper.toDto(field);
    }

    public FieldResponseDTO findByIdWithResponse(Long id) {
        return fieldRepository.findById(id)
                .map(fieldMapper::toResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("Field not found with id: " + id));
    }
}
