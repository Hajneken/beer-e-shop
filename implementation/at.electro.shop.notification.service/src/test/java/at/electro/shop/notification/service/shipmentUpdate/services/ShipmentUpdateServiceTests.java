package at.electro.shop.notification.service.shipmentUpdate.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import at.electro.shop.notification.service.services.ShipmentNotificationService;
import at.electro.shop.notification.service.shipmentUpdate.models.Address;
import at.electro.shop.notification.service.shipmentUpdate.models.ShipmentStatus;
import at.electro.shop.notification.service.shipmentUpdate.models.ShipmentUpdate;

public class ShipmentUpdateServiceTests {
    private final ShipmentNotificationService shipmentNotificationService = mock(ShipmentNotificationService.class);

    private ShipmentUpdateService sut;

    @BeforeEach
    public void setUp() {
        sut = new ShipmentUpdateService(shipmentNotificationService);
    }

    @Test
    public void processShipmentUpdate_AddressInUpdateIsNull_ThrowsIllegalArgumentException() {
        // Arrange
        var update = new ShipmentUpdate();
        // Act

        // Assert
        assertThrows(IllegalArgumentException.class, new Executable() {

            @Override
            public void execute() throws Throwable {
                sut.processShipmentUpdate(update);
            }
        });
    }

    @Test
    public void processShipmentUpdate_ShipmentUpdateIsNull_ThrowsIllegalArgumentException() {
        // Arrange
        var update = (ShipmentUpdate) null;
        // Act

        // Assert
        assertThrows(IllegalArgumentException.class, new Executable() {

            @Override
            public void execute() throws Throwable {
                sut.processShipmentUpdate(update);
            }
        });
    }

    @Test
    public void processShipmentUpdate_UpdateIsValid_SendsOneNotification() {
        // Arrange
        var update = new ShipmentUpdate();
        update.setAddress(new Address());
        update.setStatus(ShipmentStatus.OutForDelivery);
        // Act
        sut.processShipmentUpdate(update);
        // Assert
        verify(shipmentNotificationService, times(1)).send(any(), any());
    }
}
