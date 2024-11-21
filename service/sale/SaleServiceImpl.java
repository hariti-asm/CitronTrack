package ma.hariti.asmaa.wrm.citrontrack.service.sale;

import jakarta.persistence.EntityNotFoundException;
import ma.hariti.asmaa.wrm.citrontrack.dto.sale.SaleDTO;
import ma.hariti.asmaa.wrm.citrontrack.entity.Harvest;
import ma.hariti.asmaa.wrm.citrontrack.entity.Sale;
import ma.hariti.asmaa.wrm.citrontrack.mapper.CustomerMapper;
import ma.hariti.asmaa.wrm.citrontrack.mapper.SaleMapper;
import ma.hariti.asmaa.wrm.citrontrack.repository.HarvestRepository;
import ma.hariti.asmaa.wrm.citrontrack.repository.SaleRepository;
import ma.hariti.asmaa.wrm.citrontrack.util.GenericDtoServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SaleServiceImpl extends GenericDtoServiceImpl<SaleDTO, Sale, Long> implements SaleService {

    private final SaleRepository saleRepository;
    private final HarvestRepository harvestRepository;
    private final SaleMapper saleMapper;
    private final CustomerMapper customerMapper;

    public SaleServiceImpl(SaleRepository saleRepository, HarvestRepository harvestRepository, SaleMapper saleMapper, CustomerMapper customerMapper) {
        super(saleRepository);
        this.saleRepository = saleRepository;
        this.harvestRepository = harvestRepository;
        this.saleMapper = saleMapper;
        this.customerMapper = customerMapper;
    }

    @Override
    protected SaleDTO toDto(Sale entity) {
        return saleMapper.toDTO(entity);
    }

    @Override
    protected Sale toEntity(SaleDTO dto) {
        return saleMapper.toEntity(dto);
    }

    @Override
    protected void updateEntity(Sale entity, SaleDTO dto) {
        entity.setUnitPrice(dto.getUnitPrice());
        entity.setQuantitySold(dto.getQuantitySold());
        entity.setDateSold(dto.getDateSold());

        if (dto.getCustomer() != null) {
            entity.setCustomer(customerMapper.toEntity(dto.getCustomer()));
        } else {
            entity.setCustomer(null);
        }

        Harvest harvest = harvestRepository.findById(dto.getHarvest().getId())
                .orElseThrow(() -> new EntityNotFoundException("Harvest not found"));
        entity.setHarvest(harvest);
    }

    @Override
    @Transactional
    public SaleDTO create(SaleDTO dto) {
        Harvest harvest = harvestRepository.findById(dto.getHarvest().getId())
                .orElseThrow(() -> new EntityNotFoundException("Harvest not found"));

        if (harvest.getTotalQuantity() < dto.getQuantitySold()) {
            throw new IllegalArgumentException("Insufficient harvest quantity for sale");
        }

        harvest.setTotalQuantity(harvest.getTotalQuantity() - dto.getQuantitySold());
        harvestRepository.save(harvest);

        return super.create(dto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SaleDTO> findById(Long id) {
        return super.findById(id);
    }

    @Override
    @Transactional
    public SaleDTO update(Long id, SaleDTO dto) {
        return super.update(id, dto);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Sale existingSale = saleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sale not found"));

        Harvest harvest = existingSale.getHarvest();
        harvest.setTotalQuantity(harvest.getTotalQuantity() + existingSale.getQuantitySold());
        harvestRepository.save(harvest);

        super.deleteById(id);
    }

    @Override
    @Transactional
    public List<SaleDTO> createAll(List<SaleDTO> dtos) {
        return super.createAll(dtos);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return super.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SaleDTO> findAll() {
        return super.findAll();
    }

    @Override
    @Transactional
    public void delete(SaleDTO dto) {
        Sale existingSale = saleRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Sale not found"));

        Harvest harvest = existingSale.getHarvest();
        harvest.setTotalQuantity(harvest.getTotalQuantity() + existingSale.getQuantitySold());
        harvestRepository.save(harvest);

        super.delete(dto);
    }

    @Override
    @Transactional
    public void deleteAll(List<SaleDTO> dtos) {
        dtos.forEach(this::delete);
    }

}
