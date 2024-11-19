package ma.hariti.asmaa.wrm.citrontrack.service.farm;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import ma.hariti.asmaa.wrm.citrontrack.dto.harvest.HarvestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.harvest.HarvestRequestDTO;
import ma.hariti.asmaa.wrm.citrontrack.entity.Harvest;
import ma.hariti.asmaa.wrm.citrontrack.mapper.HarvestMapper;
import ma.hariti.asmaa.wrm.citrontrack.repository.HarvestRepository;
import ma.hariti.asmaa.wrm.citrontrack.service.harvest.HarvestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HarvestServiceImpl implements HarvestService {

    private final HarvestRepository harvestRepository;
    private final HarvestMapper harvestMapper;

    @Transactional
    public HarvestDTO createHarvest(HarvestRequestDTO harvestRequestDTO) {
        Harvest harvest = harvestMapper.requestDtoToEntity(harvestRequestDTO);
        Harvest savedHarvest = harvestRepository.save(harvest);
        return harvestMapper.toDto(savedHarvest);
    }

    @Transactional(readOnly = true)
    public HarvestDTO getHarvestById(Long id) {
        Harvest harvest = harvestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Harvest not found with id: " + id));
        return harvestMapper.toDto(harvest);
    }

    @Transactional(readOnly = true)
    public List<HarvestDTO> getAllHarvests() {
        List<Harvest> harvests = harvestRepository.findAll();
        return harvests.stream()
                .map(harvestMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public HarvestDTO updateHarvest(Long id, HarvestDTO harvestDTO) {
        Harvest harvest = harvestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Harvest not found with id: " + id));
        harvestMapper.updateEntityFromDto(harvestDTO, harvest);
        Harvest updatedHarvest = harvestRepository.save(harvest);
        return harvestMapper.toDto(updatedHarvest);
    }

    @Transactional
    public void deleteHarvest(Long id) {
        Harvest harvest = harvestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Harvest not found with id: " + id));
        harvestRepository.delete(harvest);
    }

    @Override
    public HarvestDTO create(HarvestDTO dto) {
        Harvest harvest = harvestMapper.toEntity(dto);
        Harvest savedHarvest = harvestRepository.save(harvest);
        return harvestMapper.toDto(savedHarvest);
    }

    @Override
    public List<HarvestDTO> createAll(List<HarvestDTO> dtos) {
        List<Harvest> harvests = dtos.stream()
                .map(harvestMapper::toEntity)
                .collect(Collectors.toList());
        List<Harvest> savedHarvests = harvestRepository.saveAll(harvests);
        return savedHarvests.stream()
                .map(harvestMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<HarvestDTO> findById(Long id) {
        return harvestRepository.findById(id)
                .map(harvestMapper::toDto);
    }

    @Override
    public List<HarvestDTO> findAll() {
        List<Harvest> harvests = harvestRepository.findAll();
        return harvests.stream()
                .map(harvestMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<HarvestDTO> findAll(Pageable pageable) {
        Page<Harvest> harvestPage = harvestRepository.findAll(pageable);
        return harvestPage.map(harvestMapper::toDto);
    }

    @Override
    public boolean existsById(Long id) {
        return harvestRepository.existsById(id);
    }

    @Override
    public long count() {
        return harvestRepository.count();
    }

    @Override
    public List<HarvestDTO> findAllById(List<Long> ids) {
        List<Harvest> harvests = harvestRepository.findAllById(ids);
        return harvests.stream()
                .map(harvestMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public HarvestDTO update(Long id, HarvestDTO dto) {
        Harvest harvest = harvestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Harvest not found with id: " + id));
        harvestMapper.updateEntityFromDto(dto, harvest);
        Harvest updatedHarvest = harvestRepository.save(harvest);
        return harvestMapper.toDto(updatedHarvest);
    }

    @Override
    public void deleteById(Long id) {
        if (!harvestRepository.existsById(id)) {
            throw new EntityNotFoundException("Harvest not found with id: " + id);
        }
        harvestRepository.deleteById(id);
    }

    @Override
    public void delete(HarvestDTO dto) {
        Harvest harvest = harvestMapper.toEntity(dto);
        harvestRepository.delete(harvest);
    }

    @Override
    public void deleteAll(List<HarvestDTO> dtos) {
        List<Harvest> harvests = dtos.stream()
                .map(harvestMapper::toEntity)
                .collect(Collectors.toList());
        harvestRepository.deleteAll(harvests);
    }

    @Override
    public void deleteAll() {
        harvestRepository.deleteAll();
    }
}
