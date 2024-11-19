package ma.hariti.asmaa.wrm.citrontrack.util;

import jakarta.validation.Valid;
import ma.hariti.asmaa.wrm.citrontrack.dto.field.FieldRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface GenericDtoService<D, ID> {
    D create(D dto);
    List<D> createAll(List<D> dtos);

    Optional<D> findById(ID id);
    List<D> findAll();
    Page<D> findAll(Pageable pageable);
    boolean existsById(ID id);
    long count();
    List<D> findAllById(List<ID> ids);

    D update(ID id, D dto);

    void deleteById(ID id);
    void delete(D dto);
    void deleteAll(List<D> dtos);
    void deleteAll();
}