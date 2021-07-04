package at.electro.shop.bff.api.notification.models;

public class ProductMarkingResponse extends ProductMarkingRequest {
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
