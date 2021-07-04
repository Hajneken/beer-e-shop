

package at.electro.shop.service.shipment.dao;

import at.electro.shop.service.shipment.dao.data.Publisher;
import at.electro.shop.service.shipment.dao.data.ShipmentRepository;
import at.electro.shop.service.shipment.dao.models.ShipmentEntity;
import at.electro.shop.service.shipment.mapper.ShipmentMapper;
import at.electro.shop.service.shipment.models.Shipment;
import at.electro.shop.service.suppliers.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShipmentDaoTest {

    @Mock
    private ShipmentMapper shipmentMapper;

    @Mock
    private ShipmentRepository shipmentRepository;

    @Mock
    private Publisher publisher;

    @InjectMocks
    private ShipmentDao shipmentDao;

    @Test
    @DisplayName("Should create shipment")
    public void shouldCreateShipment() {
        when(shipmentMapper.toDao(any(Shipment.class))).thenReturn(ShipmentSupplier.shipmentEntityMock());
        when(shipmentMapper.toDto(any(ShipmentEntity.class))).thenReturn(ShipmentSupplier.shipmentMock());

        shipmentDao.create(ShipmentSupplier.shipmentMock());

        verify(shipmentRepository).save(any());
        verify(publisher).publishShipmentUpdate(any());
    }

    @Test
    @DisplayName("Should update shipment status")
    public void shouldUpdateShipmentStatus() {
        when(shipmentRepository.findByUuid(any())).thenReturn(ShipmentSupplier.shipmentEntityMock());
        when(shipmentMapper.toDto(any(ShipmentEntity.class))).thenReturn(ShipmentSupplier.shipmentMock());

        shipmentDao.updateShipmentByUuid(ShipmentSupplier.shipmentMock().getUuid(), ShipmentSupplier.shipmentMock().getStatus());

        verify(shipmentRepository).save(any());
        verify(publisher).publishShipmentUpdate(any());
    }
}

