package at.electro.shop.notification.service.productMarking.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import at.electro.shop.notification.service.productMarking.dao.models.ProductMarkingEntity;
import at.electro.shop.notification.service.productMarking.mapper.ProductMarkingMapper;
import at.electro.shop.notification.service.productMarking.repository.ProductMarkingRepository;

@ExtendWith(MockitoExtension.class)
public class ProductMarkingDAOTests {
    @Mock
    private ProductMarkingMapper mapper;

    @Mock
    private ProductMarkingRepository productMarkingRepository;

    @InjectMocks
    private ProductMarkingDAO sut;

    @Test
    @DisplayName("Should Get ProductMarking By Id")
    public void shouldProductMarkingById() {
        String id = "1234-1234-1234-1234";
        sut.getProductMarkingByProductId(id);

        verify(productMarkingRepository).getByProduct(any());
        verify(mapper).toDtoList(any());
    }

    @Test
    @DisplayName("Should add ProductMarking and result has an id")
    public void shouldAddProductMarkingAndResultHasAnId() {
        var productMarking = ProductMarkingDAOTestModels.getProductMarking();
        var entity = ProductMarkingMapper.INSTANCE.toDao(productMarking);
        entity.setUuid(UUID.randomUUID().toString());
        var savedMarking = ProductMarkingMapper.INSTANCE.toDto(entity);

        when(mapper.toDao(any())).thenReturn(entity);
        when(mapper.toDto(any(ProductMarkingEntity.class))).thenReturn(savedMarking);
        when(productMarkingRepository.save(any())).thenReturn(entity);

        var result = sut.saveMarking(productMarking);

        verify(productMarkingRepository).save(any());
        verify(mapper).toDao(any());
        verify(mapper).toDto(any(ProductMarkingEntity.class));
        assertNull(productMarking.getUuid());
        assertNotNull(result.getUuid());
    }

}
