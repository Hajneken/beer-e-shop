package at.electro.shop.notification.service.shipmentUpdate.models;

public enum ShipmentStatus {
    OrderConfirmation("Order confirmed"), OutForDelivery("Out for delivery"), Delivered("Delivered");

    private String name;

    private ShipmentStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
