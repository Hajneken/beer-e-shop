package at.electro.shop.service.shipment.converter;

import at.electro.shop.service.api.models.ShipmentUpdate;
import at.electro.shop.service.suppliers.TransactionSupplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class ShipmentConverterTest {

    private ShipmentConverter shipmentConverter = new ShipmentConverter();

    
    @Test
    @DisplayName("Should convert products of transactions into shipments")
    void shouldConvertProductsOfTransactionsIntoShipments() {
        List<ShipmentUpdate> shipments = shipmentConverter.convertTransactionToShipment(TransactionSupplier.transactions());

        Assertions.assertEquals(1, shipments.size());
        Assertions.assertEquals("321f737d-f8dd-4c9d-aca8-b8c6164a1dd1", shipments.get(0).getProduct());
    }
}