package at.electro.shop.service.shipment.services;

import at.electro.shop.service.api.models.ShipmentUpdate;
import at.electro.shop.service.shipment.dao.ShipmentDao;
import at.electro.shop.service.shipment.mapper.ShipmentMapper;
import at.electro.shop.service.suppliers.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShipmentServiceTest {

    @Mock
    private ShipmentMapper shipmentMapper;

    @Mock
    private ShipmentDao shipmentDao;

    @InjectMocks
    private ShipmentService shipmentService;

    @Test
    @DisplayName("Should get all shipments")
    public void shouldGetAllShipments() {
        when(shipmentDao.fetchAll()).thenReturn(List.of(ShipmentSupplier.shipmentMock()));
        when(shipmentMapper.toApiList(any())).thenReturn(List.of(ShipmentSupplier.shipmentUpdateMock()));

        shipmentService.get();

        verify(shipmentDao).fetchAll();
        verify(shipmentMapper).toApiList(any());
    }

    @Test
    @DisplayName("Should confirm order")
    public void shouldConfirmOrder() {
        shipmentService.confirmOrder(List.of(ShipmentSupplier.shipmentUpdateMock()));

        verify(shipmentDao, times(1)).create(any());
        verify(shipmentMapper, times(1)).toDto(any(ShipmentUpdate.class));
    }

    @Test
    @DisplayName("Should update shipment status")
    public void shouldUpdateShipmentStatus() {
        shipmentService.updateShipmentStatusByUuid(ShipmentSupplier.shipmentUpdateMock().getUuid(),
                ShipmentSupplier.shipmentUpdateMock().getStatus());

        verify(shipmentDao).updateShipmentByUuid(any(), any());
    }
}