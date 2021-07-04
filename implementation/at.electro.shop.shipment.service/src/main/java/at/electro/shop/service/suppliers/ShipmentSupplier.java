package at.electro.shop.service.suppliers;


import at.electro.shop.service.api.models.ShipmentStatus;
import at.electro.shop.service.api.models.ShipmentUpdate;
import at.electro.shop.service.shipment.dao.models.ShipmentEntity;
import at.electro.shop.service.shipment.models.Shipment;

public class ShipmentSupplier {

    private ShipmentSupplier() {

    }

    public static ShipmentUpdate shipmentUpdateMock() {
        ShipmentUpdate shipmentUpdate = new ShipmentUpdate();
        shipmentUpdate.setUuid("008865a4-8348-11eb-8dcd-0242ac130003");
        shipmentUpdate.setAddress(AddressSupplier.addressMock());
        shipmentUpdate.setProduct("4c68e51d-04d5-4c3d-b943-79e7fcaea92f");
        shipmentUpdate.setStatus(ShipmentStatus.OutForDelivery);

        return shipmentUpdate;
    }

    public static ShipmentEntity shipmentEntityMock() {
        ShipmentEntity shipmentEntity = new ShipmentEntity();
        shipmentEntity.setUuid("008865a4-8348-11eb-8dcd-0242ac130003");
        shipmentEntity.setAddress(AddressSupplier.addressEntityMock());
        shipmentEntity.setProduct("4c68e51d-04d5-4c3d-b943-79e7fcaea92f");
        shipmentEntity.setStatus(ShipmentStatus.OutForDelivery);

        return shipmentEntity;
    }

    public static Shipment shipmentMock() {
        Shipment shipment = new Shipment();
        shipment.setUuid("008865a4-8348-11eb-8dcd-0242ac130003");
        shipment.setAddress(AddressSupplier.addressMock());
        shipment.setProduct("4c68e51d-04d5-4c3d-b943-79e7fcaea92f");
        shipment.setStatus(ShipmentStatus.OutForDelivery);

        return shipment;
    }
}
