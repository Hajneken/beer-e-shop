package at.electro.shop.notification.service.productMarking.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import at.electro.shop.notification.service.productMarking.ProductMarking;
import at.electro.shop.notification.service.productMarking.mapper.ProductMarkingMapper;
import at.electro.shop.notification.service.productMarking.repository.ProductMarkingRepository;

@Service
public class ProductMarkingDAO {
    private ProductMarkingMapper productMarkingMapper;
    private final ProductMarkingRepository productMarkingRepository;

    public ProductMarkingDAO(ProductMarkingRepository productMarkingRepository,
            ProductMarkingMapper productMarkingMapper) {
        this.productMarkingRepository = productMarkingRepository;
        this.productMarkingMapper = productMarkingMapper;
    }

    public List<ProductMarking> getProductMarkingByProductId(String id) {
        return productMarkingMapper.toDtoList(productMarkingRepository.getByProduct(id));
    }

    public ProductMarking saveMarking(ProductMarking marking) {
        var entity = productMarkingMapper.toDao(marking);
        entity.setUuid(UUID.randomUUID().toString());
        var result = productMarkingRepository.save(entity);
        return productMarkingMapper.toDto(result);
    }

    public List<ProductMarking> fetchByUser(String user) {
        var result = productMarkingRepository.getByUser(user);
        return productMarkingMapper.toDtoList(result);
    }

    public void delete(String id) {
        var entity = productMarkingRepository.getByUuid(id);
        if (!entity.isPresent()) {
            return;
        }
        productMarkingRepository.delete(entity.get());
    }
}
