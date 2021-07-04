package at.electro.shop.notification.service.productMarking.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import at.electro.shop.notification.service.productMarking.ProductMarking;
import at.electro.shop.notification.service.productMarking.api.models.ProductChangeEvent;
import at.electro.shop.notification.service.productMarking.api.models.ProductMarkingRequest;
import at.electro.shop.notification.service.productMarking.dao.ProductMarkingDAO;
import at.electro.shop.notification.service.productMarking.mapper.ProductChangeMapper;
import at.electro.shop.notification.service.productMarking.mapper.ProductMarkingMapper;
import at.electro.shop.notification.service.productMarking.models.ProductChange;
import at.electro.shop.notification.service.services.ProductUpdateNotificationService;

@ExtendWith(MockitoExtension.class)
class ProductMarkingServiceTests {
    @Mock
    private ProductMarkingMapper productMarkingMapper;

    @Mock
    private ProductChangeMapper productChangeMapper;

    @Mock
    private ProductMarkingDAO productMarkingDAO;

    @Mock
    private ProductUpdateNotificationService notificationService;

    @InjectMocks
    private ProductMarkingService sut;

    @Test
    void addProductMarking_RequestIsValidAndRepositoryAddsProductMarkingSuccessfully_returnsAddedProductMarkingWithExtraUUID() {
        // Arrange
        var email = "emailAddress@test.com";
        var price = new BigDecimal(10.1);
        var productId = "productId";
        var userId = "userUuid";

        var req = ProductMarkingServiceTestModels.getProductMarkingRequest();
        var productMarking = ProductMarkingServiceTestModels.getProductMarking();

        var response = ProductMarkingMapper.INSTANCE.toApi(productMarking);

        when(productMarkingMapper.toApi(any())).thenReturn(response);
        when(productMarkingDAO.saveMarking(any())).thenReturn(productMarking);

        // Act
        var result = sut.addProductMarking(req);

        // Assert
        assertEquals(email, result.getEmailAddress());
        assertEquals(price, result.getPrice());
        assertEquals(productId, result.getProduct());
        assertNotNull(result.getUuid());
        assertEquals(userId, result.getUser());
        verify(productMarkingMapper).toApi(any());
    }

    @Test
    void addProductMarking_RequestIsNull_ThrowsIllegalArgumentException() {
        // Arrange
        ProductMarkingRequest req = null;

        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, new Executable() {

            @Override
            public void execute() throws Throwable {
                sut.addProductMarking(req);
            }
        });
    }

    @Test
    void notifyUsersForChange_RepoReturnsTwoEntitiesAndProductChangeHasSmallerPriceThanAnyEntity_CallsNotificationServiceTwice() {
        // Arrange
        ProductChangeEvent event = new ProductChangeEvent();
        event.setPrice(new BigDecimal(1.0));
        event.setUuid(UUID.randomUUID().toString());
        event.productName("Alibaba");

        var change = ProductChangeMapper.INSTANCE.toDto(event);

        when(productChangeMapper.toDto(any())).thenReturn(change);
        when(productMarkingDAO.getProductMarkingByProductId(any()))
                .thenReturn(ProductMarkingServiceTestModels.getTwoProductMarkings());

        // Act
        sut.notifyUsersForChange(event);

        // Assert
        verify(productChangeMapper).toDto(any());
        verify(notificationService, times(2)).send(any(), any());
    }

    @Test
    void notifyUsersForChange_RepoReturnsTwoEntitiesAndProductChangeHasSmallerPriceThanOneEntity_CallsNotificationServiceOnce() {
        // Arrange
        ProductChangeEvent event = new ProductChangeEvent();
        event.setPrice(new BigDecimal(20.0));
        event.setUuid(UUID.randomUUID().toString());
        event.productName("Alibaba");

        var change = ProductChangeMapper.INSTANCE.toDto(event);

        when(productChangeMapper.toDto(any())).thenReturn(change);
        when(productMarkingDAO.getProductMarkingByProductId(any()))
                .thenReturn(ProductMarkingServiceTestModels.getTwoProductMarkings());

        // Act
        sut.notifyUsersForChange(event);

        // Assert
        verify(productChangeMapper).toDto(any());
        verify(notificationService, times(1)).send(any(), any());
    }

    @Test
    void notifyUsersForChange_RepoReturnsTwoEntitiesAndProductChangeIsNotCheapEnough_DoesNotCallSendProductNotification() {
        // Arrange
        ProductChangeEvent event = new ProductChangeEvent();
        event.setPrice(new BigDecimal(40.0));
        event.setUuid(UUID.randomUUID().toString());
        event.productName("Alibaba");

        var change = ProductChangeMapper.INSTANCE.toDto(event);

        when(productChangeMapper.toDto(any())).thenReturn(change);
        when(productMarkingDAO.getProductMarkingByProductId(any()))
                .thenReturn(ProductMarkingServiceTestModels.getTwoProductMarkings());

        // Act
        sut.notifyUsersForChange(event);

        // Assert
        verify(productChangeMapper).toDto(any());
        verify(notificationService, never()).send(any(), any());
    }

    @Test
    void notifyUsersForChange_EventIsValidAndRepoReturnsNoEntities_DoesNotCallNotificationService() {
        // Arrange
        ProductChangeEvent event = new ProductChangeEvent();
        event.setPrice(new BigDecimal(20.0));
        event.setUuid(UUID.randomUUID().toString());
        event.productName("Alibaba");

        when(productChangeMapper.toDto(any())).thenReturn(new ProductChange());
        when(productMarkingDAO.getProductMarkingByProductId(any())).thenReturn(new ArrayList<ProductMarking>());

        // Act
        sut.notifyUsersForChange(event);

        // Assert
        verify(productChangeMapper).toDto(any());
        verify(notificationService, never()).send(any(), any());
    }

    @Test
    void notifyUsersForChange_EventIsNull_ThrowsIllegalArgumentException() {
        // Arrange
        ProductChangeEvent event = null;

        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, new Executable() {

            @Override
            public void execute() throws Throwable {
                sut.notifyUsersForChange(event);
            }
        });
    }

    @Test
    void getByUserId_idIsValid_GetsListOfProductMarkings() {
        // Arrange
        String id = "1324-1234-1234-1234";

        // Act
        sut.getByUserId(id);
        // Assert

        verify(productMarkingDAO).fetchByUser(id);
        verify(productMarkingMapper).toApiList(any());
    }

    @Test
    void getByUserId_idIsEmptyString_ThrowsIllegalArgumentException() {
        // Arrange
        String id = "";

        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, new Executable() {

            @Override
            public void execute() throws Throwable {
                sut.getByUserId(id);
            }
        });
    }

    @Test
    void getByUserId_idIsNull_ThrowsIllegalArgumentException() {
        // Arrange
        String id = (String) null;

        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, new Executable() {

            @Override
            public void execute() throws Throwable {
                sut.getByUserId(id);
            }
        });
    }

    @Test
    void delete_idIsValid_DeletesSuccessfully() {
        // Arrange
        String id = "1324-1234-1234-1234";

        // Act
        sut.delete(id);

        // Assert
        verify(productMarkingDAO).delete(id);
    }

    @Test
    void delete_idIsNull_ThrowsIllegalArgumentException() {
        // Arrange
        String id = (String) null;

        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, new Executable() {

            @Override
            public void execute() throws Throwable {
                sut.delete(id);
            }
        });
    }

    @Test
    void delete_idIsEmpty_ThrowsIllegalArgumentException() {
        // Arrange
        String id = "";

        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, new Executable() {

            @Override
            public void execute() throws Throwable {
                sut.delete(id);
            }
        });
    }

}
