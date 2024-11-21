package ma.hariti.asmaa.wrm.citrontrack.service.sale;

import jakarta.persistence.EntityNotFoundException;
import ma.hariti.asmaa.wrm.citrontrack.dto.sale.SaleDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.sale.SaleRequestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.sale.SaleResponseDTO;
import ma.hariti.asmaa.wrm.citrontrack.entity.Customer;
import ma.hariti.asmaa.wrm.citrontrack.entity.Harvest;
import ma.hariti.asmaa.wrm.citrontrack.entity.HarvestDetail;
import ma.hariti.asmaa.wrm.citrontrack.entity.Sale;
import ma.hariti.asmaa.wrm.citrontrack.mapper.CustomerMapper;
import ma.hariti.asmaa.wrm.citrontrack.mapper.HarvestMapper;
import ma.hariti.asmaa.wrm.citrontrack.mapper.SaleMapper;
import ma.hariti.asmaa.wrm.citrontrack.repository.CustomerRepository;
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
    private final CustomerRepository customerRepository;
    private final HarvestMapper harvestMapper;

    public SaleServiceImpl(SaleRepository saleRepository, HarvestRepository harvestRepository,
                           SaleMapper saleMapper, CustomerMapper customerMapper,
                           CustomerRepository customerRepository, HarvestMapper harvestMapper) {
        super(saleRepository);
        this.saleRepository = saleRepository;
        this.harvestRepository = harvestRepository;
        this.saleMapper = saleMapper;
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
        this.harvestMapper = harvestMapper;
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


    @Transactional
    public SaleResponseDTO createFromRequest(SaleRequestDTO requestDTO) {
        Customer customer = customerRepository.findById(requestDTO.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        Harvest harvest = harvestRepository.findById(requestDTO.getHarvestId())
                .orElseThrow(() -> new EntityNotFoundException("Harvest not found"));

        validateSufficientHarvestQuantity(requestDTO, harvest);

        updateHarvestQuantity(harvest, requestDTO.getQuantitySold());

        SaleDTO saleDTO = createSaleDTO(requestDTO, customer, harvest);

        SaleDTO savedSale = super.create(saleDTO);
        savedSale.setRevenue(saleDTO.getUnitPrice() * saleDTO.getQuantitySold());
        return mapToResponseDTO(savedSale);
    }

    private SaleResponseDTO mapToResponseDTO(SaleDTO saleDTO) {
        return SaleResponseDTO.builder()
                .id(saleDTO.getId())
                .unitPrice(saleDTO.getUnitPrice())
                .quantitySold(saleDTO.getQuantitySold())
                .revenue(saleDTO.getRevenue())
                .dateSold(saleDTO.getDateSold())
                .customer(saleDTO.getCustomer())
                .harvest(saleDTO.getHarvest())
                .build();
    }

    private void validateSufficientHarvestQuantity(SaleRequestDTO requestDTO, Harvest harvest) {
        double totalHarvestQuantity = harvest.getHarvestDetails().stream()
                .mapToDouble(HarvestDetail::getQuantity)
                .sum();
        if (requestDTO.getQuantitySold() > totalHarvestQuantity) {
            throw new IllegalArgumentException("Insufficient harvest quantity");
        }
    }

    private void updateHarvestQuantity(Harvest harvest, double quantitySold) {
        double totalHarvestQuantity = harvest.getHarvestDetails().stream()
                .mapToDouble(HarvestDetail::getQuantity)
                .sum();
        harvest.setTotalQuantity(totalHarvestQuantity - quantitySold);
        harvestRepository.save(harvest);
    }

    private SaleDTO createSaleDTO(SaleRequestDTO requestDTO, Customer customer, Harvest harvest) {
        double revenue = requestDTO.getUnitPrice() * requestDTO.getQuantitySold();

        return SaleDTO.builder()
                .unitPrice(requestDTO.getUnitPrice())
                .quantitySold(requestDTO.getQuantitySold())
                .dateSold(requestDTO.getDateSold())

                .customer(customerMapper.toDTO(customer))
                .harvest(harvestMapper.toDTO(harvest))
                .revenue(revenue)
                .build();
    }

    @Override
    @Transactional
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
