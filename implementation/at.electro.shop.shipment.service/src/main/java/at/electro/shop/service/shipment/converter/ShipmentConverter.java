package at.electro.shop.service.shipment.converter;

import at.electro.shop.service.api.models.ShipmentStatus;
import at.electro.shop.service.api.models.ShipmentUpdate;
import at.electro.shop.service.api.models.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ShipmentConverter {

    public List<ShipmentUpdate> convertTransactionToShipment(List<Transaction> transactions) {
        List<ShipmentUpdate> shipmentUpdates = new ArrayList<>();

        for (Transaction transaction : transactions) {
            ShipmentUpdate shipmentUpdate = new ShipmentUpdate();
            shipmentUpdate.setUuid(UUID.randomUUID().toString());
            shipmentUpdate.setAddress(transaction.getAddress());
            shipmentUpdate.setStatus(ShipmentStatus.OrderConfirmation);
            shipmentUpdate.setProduct(transaction.getProduct());
            shipmentUpdates.add(shipmentUpdate);
        }

        return shipmentUpdates;
    }
}
