package at.electro.shop.service.shipment.services;

import at.electro.shop.service.api.models.ShipmentStatus;
import at.electro.shop.service.api.models.ShipmentUpdate;
import at.electro.shop.service.shipment.dao.ShipmentDao;
import at.electro.shop.service.shipment.mapper.ShipmentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentService {

    private ShipmentMapper shipmentMapper;
    private ShipmentDao shipmentDao;

    public ShipmentService(ShipmentMapper shipmentMapper, ShipmentDao shipmentDao) {
        this.shipmentMapper = shipmentMapper;
        this.shipmentDao = shipmentDao;
    }

    public List<ShipmentUpdate> get() {
        return shipmentMapper.toApiList(shipmentDao.fetchAll());
    }

    public void confirmOrder(List<ShipmentUpdate> shipmentUpdates){
        for (ShipmentUpdate shipmentUpdate : shipmentUpdates){
            shipmentDao.create(shipmentMapper.toDto(shipmentUpdate));
        }
    }

    public void updateShipmentStatusByUuid(String uuid, ShipmentStatus shipmentStatus){
        shipmentDao.updateShipmentByUuid(uuid, shipmentStatus);
    }
}