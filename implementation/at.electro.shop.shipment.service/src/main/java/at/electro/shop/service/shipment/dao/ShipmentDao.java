package at.electro.shop.service.shipment.dao;


import at.electro.shop.service.api.models.ShipmentStatus;
import at.electro.shop.service.shipment.dao.data.Publisher;
import at.electro.shop.service.shipment.dao.data.ShipmentRepository;
import at.electro.shop.service.shipment.dao.models.ShipmentEntity;
import at.electro.shop.service.shipment.mapper.ShipmentMapper;
import at.electro.shop.service.shipment.models.Shipment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentDao {

    private ShipmentMapper shipmentMapper;
    private ShipmentRepository shipmentRepository;
    private Publisher publisher;

    public ShipmentDao(ShipmentMapper shipmentMapper, ShipmentRepository shipmentRepository, Publisher publisher) {
        this.shipmentMapper = shipmentMapper;
        this.shipmentRepository = shipmentRepository;
        this.publisher = publisher;
    }

    public List<Shipment> fetchAll() {
        return shipmentMapper.toDtoList(shipmentRepository.findAll());
    }

    public Shipment create(Shipment shipment) {
        ShipmentEntity shipmentEntity = shipmentMapper.toDao(shipment);
        shipmentRepository.save(shipmentEntity);
        publisher.publishShipmentUpdate(shipmentEntity);

        return shipmentMapper.toDto(shipmentEntity);
    }

    public Shipment updateShipmentByUuid(String uuid, ShipmentStatus shipmentStatus) {
        ShipmentEntity shipmentEntity = shipmentRepository.findByUuid(uuid);
        shipmentEntity.setStatus(shipmentStatus);
        shipmentRepository.save(shipmentEntity);
        publisher.publishShipmentUpdate(shipmentEntity);

        return shipmentMapper.toDto(shipmentEntity);
    }
}